beforeEach(function() {
  var boundingBox = new b2.AABB();
  boundingBox.lowerBound.Set( 0, 0 );
  boundingBox.upperBound.Set( 1024, 512);
  var gravity = new b2.Vec2( 0, 0 );
  ig.world = new b2.World( boundingBox, gravity, true );
  ig.input = new ig.Input();
  // ig.system.canvas = {
  //   width: 1024,
  //   height: 512
  // };


  this.addMatchers({
    toBePlaying: function(expectedSong) {
      var player = this.actual;
      return player.currentlyPlayingSong === expectedSong && 
             player.isPlaying;
    }
  });
});
