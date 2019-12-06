import pymysql.cursors

conn = pymysql.connect(host='localhost', user='root', password='123', charset='utf8mb4')

try:
    with conn.cursor() as cursor:
        sql = 'CREATE DATABASE test2'
        cursor.execute(sql)
        conn.commit()
finally:
        conn.close()

