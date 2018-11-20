CREATE TABLE TICKET (
  Ticket_ID INT NOT NULL,
  Lot_ID INT NOT NULL UNIQUE,
  Ticket_Date Date NOT NULL,
  Ticket_Time_In Time NOT NULL,
  Ticket_Time_Out Time,
  Ticket_Price DOUBLE,
  Ticket_Lost BOOLEAN,

  CONSTRAINT PK_TICKET PRIMARY KEY (Ticket_ID, Lot_ID, Ticket_Date),
  CONSTRAINT FK_LOT FOREIGN KEY (Lot_ID) REFERENCES LOT(Lot_ID)
);