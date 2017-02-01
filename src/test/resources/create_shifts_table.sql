CREATE TABLE Shifts( shift_id INT NOT NULL auto_increment,
 organization_id INT NOT NULL, donor_id INT NOT NULL, recipient_id INT NOT NULL, rescue_date DATE, volunteer_1 VARCHAR(100),
 volunteer_2 VARCHAR(100), volunteer_3 VARCHAR(100), pick_up_time TIMESTAMP, mode_of_transit VARCHAR(25),
  food_donated_weight INT, food_composted_weight INT, shift_length int, food_type_summary VARCHAR(250),
   comments VARCHAR(250), supplies_stocked tinyint(1) DEFAULT NULL, submit_time TIMESTAMP, PRIMARY KEY (shift_id),
   UNIQUE (organization_id, recipient_id, donor_id, rescue_date)

);
