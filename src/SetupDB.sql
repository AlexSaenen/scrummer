create database jdbcproject;

use jdbcproject;

create table Software_Teams(
    Software_team_name varchar(40) NOT NULL,
    Team_leader varchar(40),
    Team_formed_date date,
    Project_name varchar(40),
    PRIMARY KEY (Software_team_name)
);

create table Conference_rooms(
    Room_name varchar(40) NOT NULL,
    Room_number int NOT NULL,
    Building_name varchar(40),
    Room_phone varchar(20),
    Projector_type varchar(40),
    PRIMARY KEY (Room_name)
);

create table Meetings(
    Meeting_date date NOT NULL,
    Purpose_of_meeting varchar(256),
    Room_name varchar(40) NOT NULL,
    Software_team_name varchar(40) NOT NULL,
    FOREIGN KEY (Software_team_name) REFERENCES Software_Teams(Software_team_name),
    FOREIGN KEY (Room_name) REFERENCES Conference_rooms(Room_name),
    PRIMARY KEY (Software_team_name, Room_name, Meeting_date)
);

insert into Software_Teams values("Backlog Design", "Marco Litler", "2015-07-12", "SalfubeV2");
insert into Software_Teams values("Backlog Core", "George Michaels", "2015-07-16", "SalfubeV2");
insert into Software_Teams values("Sprint Core", "George Michaels", "2015-12-04", "SalfubeV2");
insert into Software_Teams values("Backend", "Roberts Pearse", "2016-01-12", "SalfubeV2");
insert into Software_Teams values("Frontend", "George Michaels", "2016-01-11", "SalfubeV2");
insert into Software_Teams values("Marketing", "Joseph Litler", "2015-09-08", "DemoBaramus");
insert into Software_Teams values("Communication", "Jessica Alberts", "2015-05-06", "DemoBaramus");
insert into Software_Teams values("Financials", "Francis Fert", "2015-03-08", "DemoBaramus");

insert into Conference_rooms values("VideoConference", 1, "Complexe Astride", "(562) 245-3454", "3D marcoplex");
insert into Conference_rooms values("SmallMeeting", 3, "Complexe Astride", "(562) 245-3467", "plastypus");
insert into Conference_rooms values("Lab", 15, "Lambertson", "(562) 234-7895", "3D marcoplex");
insert into Conference_rooms values("LectureHall", 43, "Complex Astride", "(562) 245-3254", "3D marcoplex");
insert into Conference_rooms values("LargeMeeting", 2, "Lambertson", "(562) 245-3454", "plastypus");

insert into Meetings values("2017-03-24", "Planning", "VideoConference", "Financials");
insert into Meetings values("2017-03-24", "Team Working Session", "SmallMeeting", "Backlog Design");
insert into Meetings values("2017-03-25", "Demo", "Lab", "Frontend");
insert into Meetings values("2017-03-26", "Other", "VideoConference", "Marketing");
insert into Meetings values("2017-04-01", "Planning", "LectureHall", "Communication");
insert into Meetings values("2017-04-01", "Other", "LargeMeeting", "Backend");
insert into Meetings values("2017-04-24", "Team Working Session", "SmallMeeting", "Sprint Core");
insert into Meetings values("2017-04-27", "Demo", "Lab", "Frontend");
insert into Meetings values("2017-05-03", "Other", "VideoConference", "Financials");
insert into Meetings values("2017-06-05", "Team Working Session", "LargeMeeting", "Backlog Core");
insert into Meetings values("2017-06-17", "Demo", "Lab", "Frontend");