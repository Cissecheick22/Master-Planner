import MySQLdb

db =  MySQLdb.connect(host="localhost", user ="root",passwd="root",db="testpythonsql")

cursor =  db.cursor()
cursor.execute("SELECT VERSION()")
data = cursor.fetchone()



def insert():
	name = raw_input("Enter the student name")
	last_name = raw_input("enter the last name")
	age = raw_input("Enter the student's age");
	sex = raw_input("Enter the sex")
	classe = raw_input("Enter the student's class")

	print "database version is ", data 

	sql = "INSERT INTO student (name , last_name , age ,sex , classe) values ('%s','%s','%s','%s','%s')" % \
 	(name , last_name , age , sex ,classe )

	try:
		cursor.execute(sql)
		db.commit()
	except:
		db.rollback()
	return


def viewallstudent():
	sql = "select * from student "
	cursor.execute(sql)
        db.commit()
	results = cursor.fetchall()
	for row in results :
		id = row[0]
		name = row[1]
		last_name = row[2]
		age = row[3]
		sex = row[4]
		classe = row[5]

		print  id , name , last_name , age , sex , classe 

	return


def viewStudentById():

	id = raw_input("Enter the id of the student ")

	sql = "select * from student where id = '%s'" % (id);
	cursor.execute(sql)
	db.commit()
	row = cursor.fetchall()
        print  row  

	return



def deleteStudentById():
	id = raw_input("Enter the id of the student ")
        sql = "delete  from student where id = '%s'" % (id);
        cursor.execute(sql)
        db.commit()
        row = cursor.fetchall()
        print  row  

        return


def updateStudentById():
        id = raw_input("Enter the id of the student tu update ")
        sql = "update student set sex='M' where id = '%s'" %  (id);
        cursor.execute(sql)
        db.commit()

        return




flag = 1 


while flag:

	print "---------Select your action--------------------------"
	print  "--------Enter 1 to create New student---------------"
	print  "--------Enter 2 to view all the student-------------"
	print  "--------Enter 3 to view a particular student--------"
	print  "--------Enter 4 to delete a partiular student-------"
	print "---------Enter 5 to update student's details---------"

	action = raw_input("Choose your option")

	if   action == '1':
		insert()
	elif action == '2':
   		viewallstudent()
	elif action == '3' :
		viewStudentById()
	elif action == '4':
		deleteStudentById()
	elif action == '5':
		updateStudentById()
	else:
		print "you didnt select the right one"


