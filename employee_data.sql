CREATE TABLE IF NOT EXISTS `employee_data` (
  `em_id` int(11) NOT NULL AUTO_INCREMENT,
  `em_firstname` text NOT NULL,
  `em_lastname` text NOT NULL,
  `em_shiftstart` text NOT NULL,
  `em_shiftend` text NOT NULL,
  `em_deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`em_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

INSERT INTO `employee_data` (`em_id`, `em_firstname`, `em_lastname`, `em_shiftstart`, `em_shiftend`, `em_deleted`) VALUES
(1, 'Test', 'User', '11:11', '11:11', 0);

INSERT INTO `employee_data` (`em_id`, `em_firstname`, `em_lastname`, `em_shiftstart`, `em_shiftend`, `em_deleted`) VALUES
(2, 'Another', 'Test', '11:11', '11:11', 0);
