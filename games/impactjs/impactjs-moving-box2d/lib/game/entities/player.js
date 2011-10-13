ig.module(
  'game.entities.player'
)
.requires(
  'impact.entity'
)
.defines(function(){

EntityPlayer = ig.Entity.extend({
  
  size: { x: 20, y: 20 },  
  collides: ig.Entity.COLLIDES.FIXED,
  animSheet: new ig.AnimationSheet( 'media/player.png', 20, 20 ),
  
  init: function( x, y, settings ) {
    this.parent( x, y, settings );
    
    this.addAnim( 'idle', 0.1, [0] );
  },

  update: function() {
    var moveUnit = 100;

    if (ig.input.state("up")) {
      this.vel.y = -moveUnit;
    } else if (ig.input.state("down")) {
      this.vel.y = moveUnit;
    } else {      
      this.vel.y = 0
    }

    if (ig.input.state("right")) {
      this.vel.x = moveUnit;
    } else if (ig.input.state("left")) {
      this.vel.x = -moveUnit;
    } else {
      this.vel.x = 0
    }


    this.parent();
  }
});

});