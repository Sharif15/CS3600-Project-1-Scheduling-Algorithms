compile:
	javac *.java

fcfs: compile
	java Driver fcfs schedule.txt

sjf: compile
	java Driver sjf schedule.txt

rr: compile
	java Driver rr rr-schedule.txt

pri: compile
	java Driver pri schedule.txt
	
pri-rr: compile
	java Driver pri-rr pri-rr-schedule.txt


clean:
	rm -f *.class