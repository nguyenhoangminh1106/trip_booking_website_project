use travel_ticket_booking_web;

SELECT * FROM travel_ticket_booking_web.users;



ALTER TABLE users AUTO_INCREMENT = 1;

delete from users where id > 0;

INSERT INTO users (id, fullName, email, phoneNumber, address, userName, `password`, role_id, `status`) VALUES
(1, 'Carolyn Daniel', 'twilliams@example.com', '1498939252', '3838 Rachel Heights', 'janicewebb', '&P0DOU@q_v', 1, true),
(2, 'John Clark', 'kescobar@example.net', '3544670270', '430 Sarah Corners', 'travis77', 'v_9aHut&y#', 1, true),
(3, 'Justin Bailey', 'longnicole@example.net', '4969026607', 'PSC 0417, Box 4895', 'anthonywhite', '2hEJBNSj+h', 1, true),
(4, 'Antonio Parrish', 'clarkrebecca@example.org', '5939497651', '8824 Jennifer Terrace', 'pattondeborah', 'z3AcFegO)r', 1, true),
(5, 'Elizabeth Fritz', 'rspears@example.org', '4312966385', '698 Debra Village', 'sarah45', 'Ow8XX8OkT@', 0, true),
(6, 'Nicholas Roach', 'clarkamy@example.org', '1756493213', '3543 Hayes Meadow', 'jenkinsrebecca', 'A^y0%fLd!Z', 1, true),
(7, 'Daryl Parks', 'ojones@example.org', '1496965345', '875 Alyssa Crescent', 'melissa42', 'f^5lFAwA@z', 0, true),
(8, 'Jennifer Mcfarland', 'andersonemily@example.com', '1460567063', '01769 Jeffrey Centers', 'ashley57', 'QYk8l5dpf_', 1, true),
(9, 'Jessica Smith', 'ocox@example.net', '1347481085', '9851 Ashley Garden', 'robertkennedy', '%&6t&KUimp', 0, true),
(10, 'Benjamin Eaton', 'bjohnson@example.net', '1218757048', '793 Campbell Plaza', 'scotthensley', 'p^4iEM9ecw', 0, true),
(11, 'Mitchell Harris', 'emily61@example.com', '9486128915', '442 Louis Cliff', 'dwest', 'V*15_Ubx$z', 0, true),
(12, 'Rick Williams', 'walshmichelle@example.com', '1983345573', '6124 Paula Inlet', 'gloverbrittany', '6dUkwcW)@w', 0, true),
(13, 'Anthony Garcia', 'johnking@example.org', '1622794762', 'USS Watson, FPO AE', 'wendy16', '6D8G6Kt1o(', 0, true),
(14, 'Jacqueline Peterson', 'ugill@example.org', '6745172299', '15977 Parks Viaduct', 'shannon78', 'QD(*S3Jc#K', 0, true),
(15, 'Melanie Williams', 'hrobertson@example.org', '1800689127', '9579 Katherine Oval', 'bmcdonald', '+2)pZV(k*Z', 1, true),
(16, 'Stephanie Hudson', 'lisacarney@example.net', '5103377041', '0633 Benjamin Villages', 'cunninghamkevin', 'j0I0pGYg)g', 1, true),
(17, 'Miranda Dixon', 'jamesjohnson@example.com', '7158091424', '1856 Dunn Street', 'dianaaustin', '+gs#pRDEc3', 0, true),
(18, 'Evan Arnold DDS', 'rebeccahall@example.net', '5326226064', '60957 Diaz Passage', 'samantha23', 'R0Go+OuG+e', 0, true),
(19, 'Dana Stephens', 'garciapatricia@example.com', '2199545582', '0177 Davis Locks', 'pinedajames', 'w(^d4_Fmru', 1, true),
(20, 'Arthur Ross DVM', 'olsonnatasha@example.com', '7125412993', '34547 Simpson Port', 'kendrafuller', '0Idpi8w!&T', 1, true),
(21, 'Brian Willis', 'lorievans@example.net', '1416242517', '4254 Ochoa Island', 'annandrews', '(2P*JqVc5w', 0, true),
(22, 'Allison Lopez', 'jasmine70@example.org', '5405764317', 'PSC 9533, Box 1075', 'yeseniawaller', 'oGV26Osog#', 0, true),
(23, 'Scott Kaiser', 'john54@example.com', '4593393918', '63331 Jones Square', 'whitejon', '50kSlTee)D', 1, true),
(24, 'Sophia Fischer', 'traymond@example.com', '7593237413', '4816 Johnson Creek', 'carlos68', '#9BBYY$qO3', 0, true),
(25, 'Sarah Taylor', 'dmendez@example.org', '3495750143', '2279 Oneill Lodge', 'athompson', '_R*RVF@k91', 1, true),
(26, 'Sarah Buckley', 'hardyrachel@example.com', '7406860481', '71232 Stephen Route', 'hernandezlaurie', '*2QAz)kdxG', 1, true),
(27, 'Mary Castillo', 'stacey66@example.org', '2994948518', '4245 Graham Valley', 'eric24', '@ARgD4aN1D', 0, true),
(28, 'Stephen Scott', 'gerald77@example.com', '5418547775', '25259 Bridges Turnpike', 'jclay', 'H0KpC4fS%4', 0, true),
(29, 'Karen Johnson', 'huntjerry@example.net', '1650783211', '135 Amanda Fords', 'sshelton', 'p7Avun_S$3', 1, true),
(30, 'Destiny York', 'ryanosborne@example.net', '8053800697', '57871 Amanda Keys', 'zherrera', 'MPr6Pbik@a', 1, true),
(31, 'Jamie Good', 'michaelsarah@example.com', '7153296494', '14649 Williams Cliff', 'jameschristensen', '&8pCH4jfc5', 0, true),
(32, 'Christine Hansen', 'amber71@example.com', '9534861732', 'Unit 4788 Box 1976', 'heather63', 'z#S8_M3zRk', 0, true),
(33, 'Jason Roy', 'michael23@example.com', '2465252360', '286 Peterson Parks', 'dmejia', '0xN)h@rX(7', 0, true),
(34, 'Frank White', 'jesse62@example.net', '1255460550', '00364 Sullivan Causeway', 'williamsaaron', 'Ol+MkGda&8', 0, true),
(35, 'Charles Jones', 'dorothystephens@example.org', '2736930700', '12601 Marsh Meadow', 'ruizkelly', 'f)7I75vJA7', 1, true),
(36, 'Lucas Wallace', 'gbarnes@example.org', '1998660847', '0178 Veronica Plains', 'ruizkelly', 'f)7I75vJA7', 0, true);
