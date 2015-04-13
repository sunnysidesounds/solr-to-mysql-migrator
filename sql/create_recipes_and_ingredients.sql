# As of 4/12/15
CREATE TABLE `recipe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `source_url` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `preparation` longtext,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `ind_id` (`id`),
  KEY `ind_title` (`title`),
  KEY `ind_url` (`source_url`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `ingredient` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `recipe_id` varchar(255) DEFAULT NULL,
  `from_recipe_ingredient` varchar(255) DEFAULT NULL,
  `generic_ingredient` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `ind_id` (`id`),
  KEY `ind_from_recipe_ingredient ` (`from_recipe_ingredient `),
  KEY `ind_generic_ingredient` (`generic_ingredient`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;