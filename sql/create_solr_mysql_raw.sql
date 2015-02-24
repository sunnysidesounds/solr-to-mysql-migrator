# Create solr mysql table
CREATE TABLE `solr_data` (
	`auto_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
	`id` varchar(255) DEFAULT NULL,
	`title` varchar(255) DEFAULT NULL,
	`url` varchar(255) DEFAULT NULL,
	`content` LONGTEXT DEFAULT NULL,
	`segment` varchar(255) DEFAULT NULL,
	`digest` varchar(255) DEFAULT NULL,
	`boost` varchar(255) DEFAULT NULL,
	`cache` varchar(255) DEFAULT NULL,
	`tstamp` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`auto_id`),
	INDEX `ind_id` (`id`),
	INDEX `ind_title` (`title`),
	INDEX `ind_url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;