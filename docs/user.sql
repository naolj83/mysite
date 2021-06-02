-- scheme
desc user;

-- join(insert)
  insert 
    into user 
  values(null, '관리자', 'admin@mysite.com', '1234', 'female');
  
  -- user list(select)
  select * from user;
  
  -- login(select)
  select no, name 
    from user 
   where email='naolj83@gmail.com' and password='1234';
   
   delete from user where no = 13;