drop database if exists food;
create database food;
use food;
CREATE TABLE tb_food
(
    id              int primary key AUTO_INCREMENT,
    food_name       varchar(25),
    production_date date,
    expiry_date     date,
    address         varchar(50),
    producer        varchar(50),
    phone           varchar(30),
    storage_id      int(10),
    risk_degree     varchar(50)
) ENGINE = InnoDB CHARACTER SET = utf8;

INSERT INTO tb_food VALUES (1, '土地', '2021-04-13', '2021-04-24', '湖南省岳阳市', '岳阳食品公司', '1561', 1, '1');
select * from tb_food;