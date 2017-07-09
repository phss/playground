# Vectors
a <- c(1, 2, 5, 3, 6, -2, 4)
b <- c("one", "two", "three")
c <- c(TRUE, TRUE, TRUE, FALSE, TRUE, FALSE)

# Matrices
y <- matrix(1:20, nrow=5, ncol=4)

cells <- c(1, 2, 3, 4)
rnames <- c("R1", "R2")
cnames <- c("C1", "C2")
mymatrix <- matrix(cells, nrow = 2, ncol = 2, byrow = TRUE, dimnames = list(rnames, cnames))

# Array - multidimensional

dim1 <- c("A1", "A2")
dim2 <- c("B1", "B2", "B3")
dim3 <- c("C1", "C2", "C3", "C4")
z <- array(1:24, c(2, 3, 4), dimnames=list(dim1, dim2, dim3))

# Data frame

patientID <- c(1, 2, 3, 4)
age <- c(25, 34, 28, 52)
diabetes <- c("Type1", "Type2", "Type1", "Type1")
diabetes <- factor(diabetes)
status <- c("Poor", "Improved", "Excellent", "Poor")
status <- factor(status, order=TRUE, levels=c("Poor", "Improved", "Excellent"))
patientdata <- data.frame(patientID, age, diabetes, status)
