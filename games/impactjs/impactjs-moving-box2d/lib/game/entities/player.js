ig.module(
  'game.entities.player'
)  
.requires(
  'impact.entity',
  'plugins.box2d.entity'
)
.defines(function(){

EntityPlayer = ig.Box2DEntity.extend({
  
  size: { x: 20, y: 20 },  
  friction: 0,
        
  type: ig.Entity.TYPE.A,
  checkAgainst: ig.Entity.TYPE.NONE,
  collides: ig.Entity.COLLIDES.NEVER, // Collision is already handled by Box2D!

  animSheet: new ig.AnimationSheet( 'media/player.png', 20, 20 ),
  
  init: function( x, y, settings ) {
    this.parent( x, y, settings );
    
    this.addAnim( 'idle', 0.1, [0] );
  },

  update: function() {
    var moveUnit = 20, moveX = 0, moveY = 0;

    if (ig.input.state("up")) {
      moveY = -moveUnit;
    } else if (ig.input.state("down")) {
      moveY = moveUnit;
    }

    if (ig.input.state("right")) {
      moveX = moveUnit;
    } else if (ig.input.state("left")) {
      moveX = -moveUnit;
    } 

    this.move(moveX, moveY);

    // this.body.SetXForm(this.body.GetPosition(), 0);

    this.parent();
  },

  move: function(x, y) {
    this.body.WakeUp();
    this.body.SetLinearVelocity(new b2.Vec2(x, y));
    // this.body.ApplyImpulse( new b2.Vec2(x, y), this.body.GetPosition() );
  }
});

});