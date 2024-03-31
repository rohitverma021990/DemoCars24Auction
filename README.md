# DemoCars24Auction
Demo Spring boot Application for Cars24 Auction System.
### Problem Statement
This is a Cars Auction Platform where multiple dealers across India can login and
place their bids on multiple running Auctions.
An Auction can be run on demand by the admin team, and as soon as the Auction starts, all
the dealers interested in the make, model of the car should be notified, so that they can
actively participate in the auction.

Every Auction runs for exactly 24 minutes, But if any dealer places a bid in the last 2 minutes
of the auction, the auction should be extended by 2 more minutes in order to allow other
dealers to place a higher bid.

If the outcome of the first auction is not satisfactory, we should be able to run another
auction. (Not Satisfactory Result will be defined by s set of rules, if any single rule is met, the
outcome will be marked “not satisfactory” and the vehicle will be eligible for another auction)
At the end of the auction, the winner should be notified and the vehicle should be allocated
to the winner.

This project is build with the help of spring boot, MySQL.
The application exposed some services which can be use via
REST apis.
1. I setup basic spring boot project with the help of spring initializr with dependencies.
2. Integrate Local MySQL with the application with the help of JDBC drivers.
3. Before running the application Cars24AuctionApplication.java please update MySQL user and password in the file src/main/resources/application.properties
4. I used threading to communicate buyers about their interest in the car based upon model and make.
5. This application also supports transaction and if transaction fail rollback will be happened accordingly.
6. I used singleton design pattern for thread pool.
7. For APIs contract I attached one file in APIsDetail.txt in resource folder.
