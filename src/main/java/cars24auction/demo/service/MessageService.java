package cars24auction.demo.service;

import cars24auction.demo.entity.Message;
import cars24auction.demo.repository.MessageRepository;
import cars24auction.demo.utils.ThreadExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String addMessage(Long auctionId, Long customerId, String content){
        Message message = new Message();
        message.setAuctionId(auctionId);
        message.setCustomerId(customerId);
        message.setContent(content);
        messageRepository.save(message);
        return String.format("Thread %s Message sent to customer : %s", Thread.currentThread(), customerId);
    }

    public List<Message> getMessage(Long customerId){
        return messageRepository.findAllByCustomerId(customerId);
    }

    public void informInterestedBuyers(List<Long> customerIds, Long auctionId){
        System.out.println("customerIds ="+ customerIds);
        String content = String.format("Auction Id : %s started now. Please join bidding", auctionId);
        List<CompletableFuture<String>> futures = customerIds.stream()
                .map(cId -> sendMessage(cId, auctionId, content)).collect(Collectors.toList());
        CompletableFuture<Void> monitorTask = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        CompletableFuture<List<String>> combinedTask = monitorTask.thenApply(
                f -> futures.stream().map(task -> task.join()).collect(Collectors.toList()));
        CompletableFuture<List<String>> response = combinedTask.thenApply(res -> res);
        try {
            System.out.println(response.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
    }

    public CompletableFuture<String> sendMessage(Long customerId, Long auctionId, String content){
        System.out.println("custId="+customerId);
        return CompletableFuture.supplyAsync(() -> {
            return addMessage(auctionId, customerId, content);
        }, ThreadExecutor.getInstance().getExecutor());
    }
}
