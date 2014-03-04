CREATE TABLE USER (
    USERNAME VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(50) NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(30) NOT NULL,
    PRIMARY KEY(USERNAME)
);

CREATE TABLE GROUPS (
    ID INT NOT NULL,
    GROUPNAME VARCHAR(20) NOT NULL,
    PRIMARY KEY(ID)
);

CREATE TABLE TIMEFRAME(
    ID INT NOT NULL,
    STARTDATE DATETIME NOT NULL,
    ENDDATE DATETIME NOT NULL,
    PRIMARY KEY(ID)
);

CREATE TABLE MEETINGROOM(
    ID INT NOT NULL,
    ROOM VARCHAR(20) NOT NULL,
    CAPACITY INT NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE GROUPMEMBER(
    USERID VARCHAR(20) NOT NULL,
    GROUPID INT NOT NULL,
    PRIMARY KEY (USERID, GROUPID),
    FOREIGN KEY (USERID) REFERENCES USER (USERNAME) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (GROUPID) REFERENCES GROUPS (ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE APPOINTMENT(
    ID INT NOT NULL,
    DESCRIPTION VARCHAR(255),
    LOCATION VARCHAR(50),
    OWNER VARCHAR(20) NOT NULL,
    TIMEFRAMEID INT NOT NULL,
    MEETINGROOMID INT,
    PRIMARY KEY(ID),
    FOREIGN KEY (OWNER) REFERENCES USER (USERNAME) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (TIMEFRAMEID) REFERENCES TIMEFRAME (ID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (MEETINGROOMID) REFERENCES MEETINGROOM (ID) ON UPDATE CASCADE
     
);

CREATE TABLE ALARM(
    ID INT NOT NULL,
    USERID VARCHAR(20) NOT NULL,
    APPOINTMENTID INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (APPOINTMENTID) REFERENCES APPOINTMENT (ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (USERID) REFERENCES USER (USERNAME) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE PARTICIPANT(
    USERID VARCHAR(20) NOT NULL,
    APPOINTMENTID INT NOT NULL,
    STATUS VARCHAR(20) NOT NULL,
    PRIMARY KEY (USERID, APPOINTMENTID),
    FOREIGN KEY (USERID) REFERENCES USER (USERNAME) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (APPOINTMENTID) REFERENCES APPOINTMENT (ID) ON DELETE CASCADE ON UPDATE CASCADE
    
);
