module Geometry
( sphereVolume
, sphereArea
, cubeVolume
, cubeArea
, cuboidArea
, cuboidVolume
) where

import qualified Geometry.Sphere as Sphere
import qualified Geometry.Cuboid as Cuboid
import qualified Geometry.Cube as Cube

sphereVolume :: Float -> Float
sphereVolume = Sphere.volume

sphereArea :: Float -> Float
sphereArea = Sphere.area

cubeVolume :: Float -> Float
cubeVolume = Cube.volume

cubeArea :: Float -> Float
cubeArea = Cube.area

cuboidVolume :: Float -> Float -> Float -> Float
cuboidVolume = Cuboid.volume

cuboidArea :: Float -> Float -> Float -> Float
cuboidArea = Cuboid.area


