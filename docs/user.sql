-- scheme
desc user;

-- join(insert)
  insert 
    into user 
  values(null, '관리자', 'admin@mysite.com', '1234', 'female');
  
  -- user list(select)
  select * from user;