-- USERS
INSERT INTO users (id,email,name) VALUES (1000,'Teszt Aladár','teszt.aladar@otpmobil.com');
INSERT INTO users (id,email,name) VALUES (2000,'Teszt Benedek','teszt.benedek@otpmobil.com');
INSERT INTO users (id,email,name) VALUES (3000,'Teszt Cecília','teszt.cecilia@otpmobil.com');

-- DEVICE HASH
INSERT INTO user_devices (user_id,device_hash) VALUES (1000,'F67C2BCBFCFA30FCCB36F72DCA22A817');
INSERT INTO user_devices (user_id,device_hash) VALUES (1000,'0F1674BD19D3BBDD4C39E14734FFB876');
INSERT INTO user_devices (user_id,device_hash) VALUES (1000,'3AE5E9658FBD7D4048BD40820B7D227D');
INSERT INTO user_devices (user_id,device_hash) VALUES (2000,'FADDFEA562F3C914DCC81956682DB0FC');
INSERT INTO user_devices (user_id,device_hash) VALUES (3000,'E68560872BDB2DF2FFE7ADC091755378');

-- USER TOKEN
INSERT INTO user_tokens (user_id,token) VALUES (1000,'dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJkY2N0MyQkNCRkNGQTMwRkNDQjM2RjcyRENBMjJBODE3');
INSERT INTO user_tokens (user_id,token) VALUES (2000,'dGVzenQuYmVuZWRla0BvdHBtb2JpbC5jb20mMjAwMCZGQURERkVBNTYyRjNDOTE0RENDODE5NTY2ODJEQjBGQw==');
INSERT INTO user_tokens (user_id,token) VALUES (3000,'dGVzenQuY2VjaWxpYUBvdHBtb2JpbC5jb20mMzAwMCZFNjg1NjA4NzJCREIyREYyRkZFN0FEQzA5MTc1NTM3OA==');
INSERT INTO user_tokens (user_id,token) VALUES (1000,'dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJjBGMTY3NEJEMTlEM0JCREQ0QzM5RTE0NzM0RkZCODc2');
INSERT INTO user_tokens (user_id,token) VALUES (1000,'dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJjNBRTVFOTY1OEZCRDdENDA0OEJENDA4MjBCN0QyMjdE');

-- USER BANK CARDS
INSERT INTO user_bank_cards (user_id,card_id,card_number,cvc,name,amount,currency) VALUES (1000,'C001',5299706965433676,123,'Teszt Aladár',1000,'HUF');
INSERT INTO user_bank_cards (user_id,card_id,card_number,cvc,name,amount,currency) VALUES (2000,'C002',5299706965433676,456,'Teszt Benedek',2000,'HUF');
INSERT INTO user_bank_cards (user_id,card_id,card_number,cvc,name,amount,currency) VALUES (3000,'C003',5299706965433676,789,'Teszt Cecília',3000,'HUF');
