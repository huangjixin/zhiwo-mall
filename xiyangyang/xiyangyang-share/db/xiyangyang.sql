SELECT * FROM guagua.mem_member;
SELECT * FROM guagua.guess_account;
SELECT * FROM guagua.guess_question;
SELECT * FROM guagua.guess_options;
SELECT * FROM guagua.guess_mem_options;

SET SQL_SAFE_UPDATES=0; 

UPDATE guess_question gq 
SET 
    gq.CHECKED = 1
WHERE
    gq.id = '1';


-- 更新猜中会员账户值现有存款和变动记录。
update  guess_account ma 
INNER JOIN
(SELECT ma.MEMBER_ID,SUM((gmo.BET_VALUE * go.BET_RATE)) AS balance,SUM(go.bet_rate * gmo.BET_VALUE) AS deposit
    FROM
        guess_options go
    LEFT JOIN guess_mem_options gmo ON go.ID = gmo.OPTION_ID
    LEFT JOIN guess_question gq ON go.GUESS_QUESTION_ID = gq.id
        AND gmo.QUESTION_ID = gq.id
    LEFT JOIN guess_account ma ON gmo.MEM_ID = ma.id
    WHERE
        gq.id = '1' AND gq.CHECKED = 0
            AND go.IS_RIGHT = 1
    GROUP BY gmo.MEM_ID) AS temp on ma.MEMBER_ID=temp.MEMBER_ID
set ma.BALANCE=ma.BALANCE+temp.balance,ma.DEPOSIT=temp.deposit;


/*批量插入競猜正确的历史账户，表明这是一个竞猜记录*/
insert into guess_account_his (description,member_id,balance,deposit,devote,type,content,rate) 
(SELECT 
   gq.NAME as description,ma.MEMBER_ID,ma.BALANCE as balance, go.bet_rate*gmo.BET_VALUE as deposit,gmo.BET_VALUE as devote,concat('赢',(go.bet_rate*gmo.BET_VALUE)) as type,go.NAME,go.BET_RATE
FROM
	guess_options go
        left JOIN
     guess_mem_options gmo
     ON go.ID = gmo.OPTION_ID
        LEFT JOIN
    guess_question gq ON go.GUESS_QUESTION_ID = gq.id
        AND gmo.QUESTION_ID = gq.id
		left join
	guess_account ma on gmo.MEM_ID=ma.id
	
WHERE
    gq.id = '1' AND gq.CHECKED = 0
        AND go.IS_RIGHT = 1
)
; 

/*批量插入競猜错误的历史账户，表明这是一个竞猜记录，猜错了就不用添加记录了*/
/*insert into guess_account_his (description,member_id,balance,deposit,devote,type,content,rate) 
(SELECT 
   gq.NAME as description,ma.MEMBER_ID,ma.BALANCE as balance, go.bet_rate*gmo.BET_VALUE as deposit,gmo.BET_VALUE as devote,concat('输',(go.bet_rate*gmo.BET_VALUE)) as type,go.NAME,go.BET_RATE
FROM
	guess_options go
        left JOIN
     guess_mem_options gmo
     ON go.ID = gmo.OPTION_ID
        LEFT JOIN
    guess_question gq ON go.GUESS_QUESTION_ID = gq.id
        AND gmo.QUESTION_ID = gq.id
		left join
	guess_account ma on gmo.MEM_ID=ma.id
	
WHERE
    gq.id = '1' AND gq.CHECKED = 0
        AND go.IS_RIGHT = 0
);*/ 
       
SELECT 
    temp.balance, temp.deposit, temp.MEMBER_ID
FROM
    (SELECT 
        gq.NAME AS description,
            ma.MEMBER_ID,
            SUM((ma.BALANCE + gmo.BET_VALUE * go.BET_RATE)) AS balance,
            SUM(go.bet_rate * gmo.BET_VALUE) AS deposit,
            gmo.BET_VALUE AS devote,
            CONCAT('赢', (go.bet_rate * gmo.BET_VALUE)) AS type,
            go.NAME,
            go.BET_RATE
    FROM
        guess_options go
    LEFT JOIN guess_mem_options gmo ON go.ID = gmo.OPTION_ID
    LEFT JOIN guess_question gq ON go.GUESS_QUESTION_ID = gq.id
        AND gmo.QUESTION_ID = gq.id
    LEFT JOIN guess_account ma ON gmo.MEM_ID = ma.id
    WHERE
        gq.id = '1' AND gq.CHECKED = 0
            AND go.IS_RIGHT = 1
    GROUP BY gmo.MEM_ID) AS temp;

        
select * from guess_account_his where MEMBER_ID='2' order by id desc;
/*查询会员答对问题的变动金额*/
SELECT 
   concat('1000',gq.NAME) as description,ma.MEMBER_ID,ma.BALANCE, go.bet_rate*gmo.BET_VALUE as deposit
FROM
	guess_options go
        left JOIN
     guess_mem_options gmo
     ON go.ID = gmo.OPTION_ID
        LEFT JOIN
    guess_question gq ON go.GUESS_QUESTION_ID = gq.id
        AND gmo.QUESTION_ID = gq.id
		left join
	guess_account ma on gmo.MEM_ID=ma.id
	
WHERE
    gq.id = '1' AND gq.CHECKED = 0
        AND go.IS_RIGHT = 0;


SELECT * FROM guagua.guess_account;

SELECT * FROM guess_account_his; 



/*查询个人的竞猜记录*/
SELECT 
	gq.NAME,
    go.NAME as option_name,
    go.BET_RATE,
    gmo.BET_VALUE,
    CASE
		WHEN go.IS_RIGHT = '1' and gq.CHECKED=1 THEN concat('赢',go.BET_RATE * gmo.BET_VALUE)
        WHEN go.IS_RIGHT = '0' and gq.CHECKED=1 THEN concat('输',go.BET_RATE * gmo.BET_VALUE)
        WHEN go.IS_RIGHT = '1' and gq.CHECKED=0 THEN '未开奖'
        WHEN go.IS_RIGHT = '0' and gq.CHECKED=0 THEN '未开奖'
        WHEN go.IS_RIGHT = '1' and gq.CHECKED=-1 THEN concat('本期没有开奖，退款',gmo.BET_VALUE)
        WHEN go.IS_RIGHT = '0' and gq.CHECKED=-1 THEN concat('本期没有开奖，退款',gmo.BET_VALUE)
    END as RESULT
FROM
    guess_mem_options gmo
		LEFT JOIN
    mem_member member ON member.id = gmo.MEM_ID
        LEFT JOIN
    guess_options go ON go.ID = gmo.OPTION_ID
        LEFT JOIN
    guess_question gq ON gq.id = gmo.QUESTION_ID
        AND go.GUESS_QUESTION_ID = gq.id
WHERE
    member.username = 'Tony' order by gq.id desc, gq.CREATE_DATE desc;
    

/*查詢會員競猜總額*/    
SELECT 
    SUM(gmo.BET_VALUE)
FROM
    mem_member member
        LEFT JOIN
    guess_mem_options gmo ON member.id = gmo.MEM_ID
WHERE
    member.username = 'Tony';
 
 /*查詢會員競猜猜中總額*/ 
SELECT 
    SUM(gmo.BET_VALUE) as '總共壓中的金額',sum(gmo.BET_VALUE*go.BET_RATE) as '獎金'
FROM
    guess_mem_options gmo
        LEFT JOIN
    mem_member member ON member.id = gmo.MEM_ID
        LEFT JOIN
    guess_options go ON go.ID = gmo.OPTION_ID
WHERE
    member.username = 'Tony' and go.IS_RIGHT='1';
 
  /*查詢會員競猜输钱總額*/ 
 SELECT 
    SUM(gmo.BET_VALUE) as '總共输钱的金額'
FROM
    guess_mem_options gmo
    LEFT JOIN
    guess_options go ON go.ID = gmo.OPTION_ID
    LEFT JOIN
    guess_question gq on gmo.QUESTION_ID=gq.id and go.GUESS_QUESTION_ID=gq.id
        LEFT JOIN
    mem_member member ON member.id = gmo.MEM_ID
WHERE
    member.username = 'Tony' and go.IS_RIGHT='0';  
    
/*查询输钱最多的人*/
SELECT 
    a.bet_value, a.MEM_ID,m.USERNAME
FROM
    (SELECT 
        SUM(gmo.BET_VALUE) AS bet_value, gmo.MEM_ID
    FROM
        guess_mem_options gmo
    LEFT JOIN guess_options go ON go.ID = gmo.OPTION_ID
    LEFT JOIN guess_question gq ON gmo.QUESTION_ID = gq.id
        AND go.GUESS_QUESTION_ID = gq.id
    WHERE
        go.IS_RIGHT = '0' AND gq.CHECKED = 1
    GROUP BY gmo.MEM_ID) AS a left join mem_member m on m.id=a.mem_id
WHERE
    a.bet_value = (SELECT 
            MAX(b.bet_value)
        FROM
            ((SELECT 
                SUM(gmo.BET_VALUE) AS bet_value, gmo.MEM_ID
            FROM
                guess_mem_options gmo
            LEFT JOIN guess_options go ON go.ID = gmo.OPTION_ID
            LEFT JOIN guess_question gq ON gmo.QUESTION_ID = gq.id
                AND go.GUESS_QUESTION_ID = gq.id
            WHERE
                go.IS_RIGHT = '0' AND gq.CHECKED = 1
            GROUP BY gmo.MEM_ID) AS b));
            
            
SELECT 
    SUM(gmo.BET_VALUE) AS bet_value, gmo.MEM_ID,m.USERNAME
FROM
    guess_mem_options gmo
        LEFT JOIN
    guess_options go ON go.ID = gmo.OPTION_ID
        LEFT JOIN
    guess_question gq ON gmo.QUESTION_ID = gq.id
        AND go.GUESS_QUESTION_ID = gq.id
	left join
    mem_member m 
    on gmo.MEM_ID=m.id
WHERE
    go.IS_RIGHT = '0' AND gq.CHECKED = 1
GROUP BY gmo.MEM_ID
HAVING SUM(gmo.BET_VALUE) = (SELECT 
        MAX(a.bet_value)
    FROM
        (SELECT 
            SUM(gmo.BET_VALUE) AS bet_value, gmo.MEM_ID
        FROM
            guess_mem_options gmo
        LEFT JOIN guess_options go ON go.ID = gmo.OPTION_ID
        LEFT JOIN guess_question gq ON gmo.QUESTION_ID = gq.id
            AND go.GUESS_QUESTION_ID = gq.id
        WHERE
            go.IS_RIGHT = '0' AND gq.CHECKED = 1
        GROUP BY gmo.MEM_ID) AS a);