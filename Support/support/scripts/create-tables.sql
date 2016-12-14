CREATE TABLE `TransactionHistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userGuid` varchar(255) DEFAULT NULL,
  `requestType` varchar(255) DEFAULT NULL,
  `requestUrl` varchar(255) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `statusCode` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1872 DEFAULT CHARSET=latin1;
