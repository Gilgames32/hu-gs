package engine.components;

import engine.GameObject;

public class Trampoline extends BoxCollider {

    public Trampoline() {
    }

    @Override
    public void onCollision(GameObject other) {
        Rigidbody rb = other.getComponent(Rigidbody.class);
        if (rb == null) {
            return;
        }

        if (rb.yVel < 0) {
            rb.yVel *= -1;
            // it would also invert the gravity added in the rb update, thus making it jump
            // higher than the initial height
            rb.yVel -= rb.gravityScale;
            Player player = other.getComponent(Player.class);
            if (player != null) {
                player.canJump = false;
            }
        }

    }
}
