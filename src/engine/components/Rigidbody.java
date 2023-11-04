package engine.components;
public class Rigidbody extends GameComponent {
    double xVel = 0;
    double yVel = 0;
    double gravityScale = 1;

    @Override
    public void update() {
        yVel -= gravityScale;



        gameObject.position.x += xVel;
        gameObject.position.y += yVel;
    }
}
