import pymysql.cursors
  
conn = pymysql.connect(host='localhost', user='root', password='123', charset='utf8mb4')
   
try:
    with conn.cursor() as cursor:
        sql = "insert into study_test.test1(Date, Note, Count)values('2019.12.07', '입력 테스트', '5')"
        cursor.execute(sql)
        rows = cursor.fetchall()
        print(rows)
        conn.commit()
finally:
    conn.close()
              
