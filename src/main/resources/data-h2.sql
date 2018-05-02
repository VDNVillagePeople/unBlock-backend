
SET REFERENTIAL_INTEGRITY FALSE;

INSERT INTO Attraction(id, description, name, status, x, y, googlePlaceId, block_id) VALUES ('de949f41-0759-470a-8279-deb29a954f6d','This place is sick as all hell! Cheap drinks, hot chicks. Everything a party animal could want. Come in for a cheap beer and a shot courtesy of UnBlock!','13th Step Bar','ATTRACTION_DISABLED',0,0, '12345', '3fca28d4-de37-4fc3-ba8a-f6a6bf2ed66d');

INSERT INTO Block(id, name, status, neighborhood_id) VALUES ('3fca28d4-de37-4fc3-ba8a-f6a6bf2ed66d','St Mark''s','BLOCK_LIVE','328826e4-8b13-4302-a9cd-5a3a3a430d18'),('da88750e-75b5-47a5-a085-f41dcd00bf25','Veselka','BLOCK_LIVE','328826e4-8b13-4302-a9cd-5a3a3a430d18');

INSERT INTO City(id, name, status) VALUES ('13eb309e-3c16-4672-9674-11b5473dbce0','Chicago','CITY_LIVE'),('1a2575c5-c1dc-4c3e-94d4-d710cf6e2d37','Portland','CITY_LIVE'),('5f6e294d-80b3-4cde-9019-dfbe0d18d4d4','Seattle','CITY_LIVE'),('6510a4f8-e9b2-450a-9dd4-8465ee6072de','St. Louis','CITY_LIVE'),('85fc209d-27bb-4938-a73f-47a81b95424d','Talahassee','CITY_LIVE'),('8bbaebb7-e140-4dbb-832b-042b91713d9c','New Orleans','CITY_LIVE'),('a4729c97-a7ed-4623-8d44-e5637dbe1ebe','Orlando','CITY_LIVE'),('b8a48a16-3288-4bb6-ab18-b7128bd98cb6','Manhattan','CITY_DISABLED'),('bf2b96f7-a5f6-4515-9201-aac7ed3e0f82','Zurich','CITY_LIVE'),('de5d3270-dedf-416b-90b0-d958c6769c59','Kansas City','CITY_LIVE');

INSERT INTO Neighborhood(id, name, status, city_id) VALUES ('18527191-4798-45c2-abeb-4668431b6d23','Freemont','NEIGHBORHOOD_STATUS_UNSPECIFIED','1a2575c5-c1dc-4c3e-94d4-d710cf6e2d37'),('2508f9ff-f645-43cb-8c9c-844904be39d5','Tribeca','NEIGHBORHOOD_LIVE','b8a48a16-3288-4bb6-ab18-b7128bd98cb6'),('328826e4-8b13-4302-a9cd-5a3a3a430d18','East Village','NEIGHBORHOOD_LIVE','b8a48a16-3288-4bb6-ab18-b7128bd98cb6'),('438fa379-040e-457e-9454-cb58eb35952b','World Trade Center ','NEIGHBORHOOD_LIVE','b8a48a16-3288-4bb6-ab18-b7128bd98cb6'),('608c5919-5f96-4893-8576-997cdbac1eef','St. louis arch area','NEIGHBORHOOD_LIVE','6510a4f8-e9b2-450a-9dd4-8465ee6072de'),('9b3c68b1-4162-4cd1-8a58-f414fd35ba1f','Double Wide Divid','NEIGHBORHOOD_LIVE','b8a48a16-3288-4bb6-ab18-b7128bd98cb6');

INSERT INTO User(id, email, level, password, username) VALUES
('60f8df65-0305-408c-b9f7-273ac93eb2eb','dan@gmail.com','ADMIN', '$2a$10$pz/yNoab1sc08iC.KGSMS.4IO.7DU/lPtHM3Ab9oAfsWfZqJOyrRW','dantheman'),
('b484369b-e885-4799-a6c1-3c1f4518268e','testUser@gmail.com', 'DEFAULT', '$2a$10$nOoG4kM.tOPTdLF1kW823.4FVt0LqRcEd.DJdSY1vGm7Iq1FHaLcy','testUser'),
('e014aef1-b419-45a7-b51c-b6db6e8d2d19','lauren@gmail.com', 'ADMIN', '$2a$10$7auwmLM14M3TJEQbK7.qOOG5CD5PiEQL2x4KXRv506R3BccC1c57u','lauren'),
('f7760b39-a1d9-4d30-bcd0-de9498796e1f','scott@gmail.com', 'ADMIN', '$2a$10$Rwm61vvwuGKB3Dn2sumSme2Sh0y9QWQClmhO.IgAQIKoH99LgOozy','scottTheBomb');

SET REFERENTIAL_INTEGRITY TRUE;
