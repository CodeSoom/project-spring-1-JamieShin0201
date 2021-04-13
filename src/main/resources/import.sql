-- 샘플 데이터
insert into product (category, description, discounted_price, name, original_price, product_id) values ('WALLET', '만두 지갑 입니다.', 18000, '만두지갑1', 20000, 1);
insert into image (product_id, url, iamge_id) values (1, 'https://res.cloudinary.com/dtcjunptq/image/upload/v1617891401/rgonw3xeyeh7oihrmjeu.jpg', 1);
insert into image (product_id, url, iamge_id) values (1, 'https://res.cloudinary.com/dtcjunptq/image/upload/v1617891428/the3oqpqmdkjleffs5kb.jpg', 6);
insert into image (product_id, url, iamge_id) values (1, 'https://res.cloudinary.com/dtcjunptq/image/upload/v1617891404/shdt9ajhyrfdoldp4zhn.jpg', 7);
insert into image (product_id, url, iamge_id) values (1, 'https://res.cloudinary.com/dtcjunptq/image/upload/v1617890985/xw87wxmmmmhccrrdxnpz.jpg', 8);

insert into keyword (name, product_id, keyword_id)values ('지갑', 1, 1);
insert into option (additional_price, name, parent_id, product_id, id) values (0, '색상', null, 1, 1);
insert into option (additional_price, name, parent_id, product_id, id) values (1000, '갈색', 1, null, 2);
insert into option (additional_price, name, parent_id, product_id, id) values (2000, '검정', 1, null, 3);

insert into product (category, description, discounted_price, name, original_price, product_id) values ('WALLET', '만두 지갑 입니다.', 18000, '만두지갑2', 20000, 2);
insert into image (product_id, url, iamge_id) values (2, 'https://res.cloudinary.com/dtcjunptq/image/upload/v1617891428/the3oqpqmdkjleffs5kb.jpg', 2);
insert into keyword (name, product_id, keyword_id)values ('지갑', 2, 2);
insert into option (additional_price, name, parent_id, product_id, id) values (0, '색상', null, 2, 4);
insert into option (additional_price, name, parent_id, product_id, id) values (1000, '갈색', 4, null, 5);
insert into option (additional_price, name, parent_id, product_id, id) values (2000, '검정', 4, null, 6);

insert into product (category, description, discounted_price, name, original_price, product_id) values ('WALLET', '만두 지갑 입니다.', 18000, '만두지갑3', 20000, 3);
insert into image (product_id, url, iamge_id) values (3, 'https://res.cloudinary.com/dtcjunptq/image/upload/v1617891404/shdt9ajhyrfdoldp4zhn.jpg', 3);
insert into keyword (name, product_id, keyword_id)values ('지갑', 3, 3);
insert into option (additional_price, name, parent_id, product_id, id) values (0, '색상', null, 3, 7);
insert into option (additional_price, name, parent_id, product_id, id) values (1000, '갈색', 7, null, 8);
insert into option (additional_price, name, parent_id, product_id, id) values (2000, '검정', 7, null, 9);

insert into product (category, description, discounted_price, name, original_price, product_id) values ('WALLET', '만두 지갑 입니다.', 18000, '만두지갑4', 20000, 4);
insert into image (product_id, url, iamge_id) values (4, 'https://res.cloudinary.com/dtcjunptq/image/upload/v1617890985/xw87wxmmmmhccrrdxnpz.jpg', 4);
insert into keyword (name, product_id, keyword_id)values ('지갑', 4, 4);
insert into option (additional_price, name, parent_id, product_id, id) values (0, '색상', null, 4, 10);
insert into option (additional_price, name, parent_id, product_id, id) values (1000, '갈색', 10, null, 11);
insert into option (additional_price, name, parent_id, product_id, id) values (2000, '검정', 10, null, 12);

insert into product (category, description, discounted_price, name, original_price, product_id) values ('WALLET', '만두 지갑 입니다.', 18000, '만두지갑5', 20000, 5);
insert into image (product_id, url, iamge_id) values (5, 'https://res.cloudinary.com/dtcjunptq/image/upload/v1617890885/bvh359gbflgdrwg6jjo9.jpg', 5);
insert into keyword (name, product_id, keyword_id)values ('지갑', 5, 5);
insert into option (additional_price, name, parent_id, product_id, id) values (0, '색상', null, 5, 13);
insert into option (additional_price, name, parent_id, product_id, id) values (1000, '갈색', 13, null, 14);
insert into option (additional_price, name, parent_id, product_id, id) values (2000, '검정', 13, null, 15);
