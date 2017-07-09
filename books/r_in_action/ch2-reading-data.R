# read.table
grades <- read.table("data/studentgrades.csv", header=T, 
                     row.names = "StudentID", sep=",",
                     colClasses = c("character", "character", "character",
                                    "numeric", "numeric", "numeric"))
