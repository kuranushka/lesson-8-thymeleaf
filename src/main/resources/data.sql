insert into account_user (username, password)
values ('user', '$2a$12$C2c/84TsWCDUs/c4Yrg8TebhgzxmSm3xkW/ofFWolVDgxmvbBDEL6');
insert into account_user (username, password)
values ('admin', '$2a$12$1V534s9FnQ5y/.AvksDKKuuJ3XkelaMtmJN45MpgU8DV7pTKubmou');
insert into account_user (username, password)
values ('manager', '$2a$12$W8FsrQP4QCDgu35hmoLGzuNmb0DwzMXPMP0UrT3zBPkpzLEiXJVBi');


insert into authority (permission)
values ('product.create'),
       ('product.read'),
       ('product.update'),
       ('product.delete'),
       ('product.add.to.cart'),
       ('product.update.in.cart'),
       ('product.delete.from.cart'),
       ('save.cart'),
       ('user.account.create'),
       ('user.account.read'),
       ('user.account.update'),
       ('user.account.delete'),
       ('manager.account.create'),
       ('manager.account.read'),
       ('manager.account.update'),
       ('manager.account.delete'),
       ('admin.account.create'),
       ('admin.account.read'),
       ('admin.account.update'),
       ('admin.account.delete');

insert into account_role (name)
values ('USER'),
       ('ADMIN'),
       ('MANAGER');

insert into user_role (user_id, role_id)
values (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 3);

insert into role_authority (authority_id, role_id)
values (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (1, 2),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 2),
       (11, 2),
       (12, 2),
       (13, 2),
       (14, 2),
       (15, 2),
       (16, 2),
       (17, 2),
       (18, 2),
       (19, 2),
       (20, 2),
       (1, 3),
       (2, 3),
       (3, 3),
       (4, 3),
       (5, 3),
       (6, 3),
       (7, 3),
       (8, 3);


insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Cranberries - Fresh', 'Niagara Mohawk Holdings, Inc.',
        'nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes',
        244.61, 'INACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Lotus Leaves', 'Dynagas LNG Partners LP',
        'velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum',
        424.06, 'ACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Bamboo Shoots - Sliced', 'Western Asset Mortgage Defined Opportunity Fund Inc',
        'libero nam dui proin leo odio porttitor id consequat in consequat ut nulla sed accumsan felis ut', 241.35,
        'INACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Rootbeer', 'BalckRock Taxable Municipal Bond Trust',
        'risus dapibus augue vel accumsan tellus nisi eu orci mauris lacinia', 401.71, 'INACTIVE', 0, null,
        '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Butter Sweet', 'Wingstop Inc.',
        'vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac', 98.43, 'ACTIVE', 0, null,
        '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Egg - Salad Premix', 'Seneca Foods Corp.',
        'at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id', 305.87, 'ACTIVE',
        0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Flour - Bread', 'Foot Locker, Inc.',
        'maecenas tristique est et tempus semper est quam pharetra magna ac consequat metus sapien', 200.85, 'INACTIVE',
        0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Wine - Cava Aria Estate Brut', 'America Movil, S.A.B. de C.V.',
        'integer pede justo lacinia eget tincidunt eget tempus vel pede morbi porttitor lorem id ligula suspendisse ornare',
        252.3, 'INACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Cheese - Colby', 'Performance Food Group Company',
        'facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit vivamus vel nulla eget eros elementum',
        426.22, 'INACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Beef - Tenderloin Tails', 'The Andersons, Inc.',
        'in felis donec semper sapien a libero nam dui proin leo odio porttitor id consequat in', 258.1, 'ACTIVE', 0,
        null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Spice - Onion Powder Granulated', 'Francesca''s Holdings Corporation',
        'eget elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus',
        484.7, 'INACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Chocolate - Unsweetened', 'Public Storage', 'ante ipsum primis in faucibus orci luctus et ultrices posuere',
        165.78, 'INACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Ginger - Fresh', 'Maxwell Technologies, Inc.',
        'luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices', 306.17, 'INACTIVE',
        0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Sour Puss Raspberry', 'Post Holdings, Inc.',
        'eget tempus vel pede morbi porttitor lorem id ligula suspendisse ornare consequat', 144.87, 'ACTIVE', 0, null,
        '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Sauce Tomato Pouch', 'American Eagle Outfitters, Inc.',
        'duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis', 463.79, 'ACTIVE', 0, null, '2022-02-14',
        null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Pasta - Cappellini, Dry', 'Codorus Valley Bancorp, Inc',
        'nulla sed vel enim sit amet nunc viverra dapibus nulla suscipit ligula in lacus curabitur at ipsum ac', 178.25,
        'ACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Salmon Steak - Cohoe 6 Oz', 'Franklin Resources, Inc.',
        'quam suspendisse potenti nullam porttitor lacus at turpis donec posuere metus vitae ipsum aliquam non mauris morbi non lectus aliquam',
        230.56, 'ACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Sprouts - Alfalfa', 'Nordstrom, Inc.',
        'quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus', 396.03,
        'ACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Cumin - Whole', 'RealPage, Inc.',
        'elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante', 328.49,
        'INACTIVE', 0, null, '2022-02-14', null, null);
insert into product (title, manufacturer, description, cost, status, version, created_by, created_date, last_modify_by,
                     last_modify_date)
values ('Chambord Royal', 'Alabama Power Company',
        'nulla suscipit ligula in lacus curabitur at ipsum ac tellus semper interdum mauris ullamcorper purus', 443.14,
        'ACTIVE', 0, null, '2022-02-14', null, null);