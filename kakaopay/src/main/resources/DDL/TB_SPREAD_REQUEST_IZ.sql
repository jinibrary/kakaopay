CREATE TABLE `TB_SPREAD_REQUEST_IZ` (
  `TOKEN` varchar(3) NOT NULL,
  `ROOM` varchar(10) NOT NULL,
  `REQ_SEQ` int NOT NULL,
  `USER` int NOT NULL,
  `RG_REQUEST_DATE` datetime NOT NULL,
  `RG_MONEY` int NOT NULL,
  `RG_USER` int NOT NULL,
  `RG_SERVE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`TOKEN`,`ROOM`,`REQ_SEQ`,`USER`,`RG_REQUEST_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci