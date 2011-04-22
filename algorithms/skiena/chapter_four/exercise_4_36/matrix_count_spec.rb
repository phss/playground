require "matrix_count"

describe MatrixCount do

  it "should return 0 for matrix without 0" do
    MatrixCount.zeros_of([[1, 2], [3, 4]]).should == 0
  end

  it "should return 1 for matrix with one row" do
    MatrixCount.zeros_of([[0]]).should == 1
  end

  it "should return 2 for matrix with two rows and 2 zeros" do
    MatrixCount.zeros_of([[0, 1], [-1, 0]]).should == 2
  end

  it "should return 2 for matrix with two rows and 2 zeros" do
    MatrixCount.zeros_of([[-10, 0, 10], [-11, -1, 5], [-12, -2, 0]]).should == 2
  end

  it "should return 1 for matrix with zero in the last element" do
    MatrixCount.zeros_of([[-10, -1, 10], [-11, -2, 5], [-12, -3, 0]]).should == 1
  end
 it "should return 1 for matrix with zero in the first row" do
    MatrixCount.zeros_of([[-10, -1, 0], [-11, -2, -1], [-12, -3, -2]]).should == 1
  end

end
