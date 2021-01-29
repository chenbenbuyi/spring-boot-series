DROP TABLE IF EXISTS `one_user`;
CREATE TABLE "one_user" (
  "id" INTEGER PRIMARY KEY AUTOINCREMENT,
  "username" TEXT NOT NULL,
  "password" TEXT NOT NULL,
  "create_time" TEXT NOT NULL,
  "update_time" TEXT,
  "desc" TEXT
);