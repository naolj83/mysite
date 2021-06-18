desc board;
desc user;

insert into board values(null, '점심?', '뭐 먹을까?', now(), 0, 0, 0, 0, 1);

select * from board;

select a.no, a.title, a.depth, a.hit, b.no, b.name
from board a, user b
where a.user_no = b.no
order by a.group_no desc, a.order_no asc;

desc guestbook;

update board
set title = '제목1', contents = '내용1'
where no = 1;

delete from board where no =2;

select max(group_no)
from board;

-- findAll
select a.no, a.title, a.contents, a.reg_date, a.hit, a.group_no, a.order_no, a.depth, a.user_no, b.name
from board a, user b
where a.user_no = b.no
order by group_no desc , order_no asc;

          
-- findByNo
select a.no, a.title, a.contents, a.reg_date, a.hit, a.group_no, a.order_no, a.depth, a.user_no, b.name
from board a, user b 
where a.user_no = b.no 
and a.no = 1; 
                    
-- updateHit
update board set hit=hit+1
where no = 1;

-- update
update board set title = ?, contents = ?, reg_date = now()
where no = ?;

select last_insert_id();

select * from user;

desc gallery;

desc site;
select * from site;

select title, welcome, profile, description
		 from site;
         
select * from user;

update user set password='1234' where no=2;

select * from gallery;