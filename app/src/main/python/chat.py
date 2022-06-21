import numpy

def main ():
 cars = ["Ford", "Volvo", "BMW"]
 return (cars)

def mai2n ():

 Matrix = (["eslam","tanta","android"],
 ["eslam1", "tanta1", "android1"],
 ["eslam2", "tanta2", "android2"]
           )
 arr1 = numpy.asarray(Matrix)
   # valid
 return (Matrix[1])

def mai2n_numpy ():

 Matrix = (["eslam",0,"android"],
 ["eslam1", 1, "android1"],
 ["eslam2", 2, "android2"]
           )
 arr1 = numpy.asarray(Matrix[1])
   # valid
 return (arr1)

#print(mai2n_numpy ())
#print(mai2n ())
