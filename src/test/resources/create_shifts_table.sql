CREATE TABLE Shifts( shift_id INT NOT NULL, donar_id int, recipient_id int, rescue_date DATE,
volunteer_1 VARCHAR(100), volunteer_2 VARCHAR(100), volunteer_3 VARCHAR(100), pick_up_time DATE, mode_of_transit VARCHAR(25),
 food_donated_weight INT, food_composted_weight INT, shift_length int, food_type_summary VARCHAR(250),
 comments VARCHAR(250), supplies_stocked tinyint(1) DEFAULT NULL, submit_time TIMESTAMP, PRIMARY KEY (shift_id),
  FOREIGN KEY (donar_id) REFERENCES Donors(donar_id), FOREIGN KEY (recipient_id) REFERENCES Recipients(recipient_id),
   UNIQUE (rescue_date, donar_id, recipient_id)
);