import pymysql.cursors
  
conn = pymysql.connect(host='localhost', user='root', password='123', charset='utf8mb4')

try:
    with conn.cursor() as cursor:
        sql = "SELECT * FROM study_test.test1"
        cursor.execute(sql)
        rows = cursor.fetchall()
        for i in rows:
            print(i)
        conn.commit()
finally:
    conn.close()
