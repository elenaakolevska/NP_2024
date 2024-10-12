//package treta;
//
//import java.util.Arrays;
//
//public class MovablesCollection {
//    public static int x_MAX;
//    public static int y_MAX;
//    public Movable[] movables;
//
//    public MovablesCollection(int x_MAX, int y_MAX) {
//        this.x_MAX = x_MAX;
//        this.y_MAX = y_MAX;
//        this.movables = new Movable[0];
//    }
//
//    public static void setxMax(int x_MAX) {
//        MovablesCollection.x_MAX = x_MAX;
//    }
//
//    public static void setyMax(int y_MAX) {
//        MovablesCollection.y_MAX = y_MAX;
//    }
//
//    public boolean isFittablePoint(int x, int y) {
//        if (x < 0 || x > x_MAX) {
//            return false;
//        }
//        if (y < 0 || y > y_MAX) {
//            return false;
//        }
//        return true;
//    }
//
//    public boolean isFittableCircle(int x, int y, int radius) {
//        if (x - radius < 0 || x + radius > x_MAX) {
//            return false;
//        }
//        if (y - radius < 0 || y + radius > y_MAX) {
//            return false;
//        }
//        return true;
//    }
//
//
//    public void addMovableObject(Movable m) throws MovableObjectNotFittableException {
//
//        if (m.getType() == TYPE.POINT) {
//            if (!(isFittablePoint(m.getCurrentXPosition(), m.getCurrentYPosition()))) {
//                throw new MovableObjectNotFittableException(String.format("Movable point with center (%d,%d) can not be fitted into the collection", m.getCurrentXPosition(), m.getCurrentYPosition()));
//            }
//        } else if (m.getType() == TYPE.CIRCLE) {
//            if (!(isFittableCircle(m.getCurrentXPosition(), m.getCurrentYPosition(), ((MovableCircle)m).radius) )){
//                throw new MovableObjectNotFittableException(String.format("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection", m.getCurrentXPosition(), m.getCurrentYPosition(), ((MovableCircle) m).radius));
//            }
//        }
//
//        movables =Arrays.copyOf(movables, movables.length +1 );
//        movables[movables.length-1]=m;
//
//    }
//
//    public void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) throws ObjectCanNotBeMovedException {
//        for (Movable m: movables) {
//            if(type == m.getType()){
//                if(direction == DIRECTION.UP){
//                    m.moveUp();
//                } else if (direction == DIRECTION.DOWN) {
//                    m.moveDown();
//                } else if (direction == DIRECTION.LEFT) {
//                    m.moveLeft();
//                } else if (direction == DIRECTION.RIGHT) {
//                    m.moveRight();
//                }
//            }
//        }
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof MovablesCollection)) return false;
//        MovablesCollection that = (MovablesCollection) o;
//        return Arrays.equals(movables, that.movables);
//    }
//
//    @Override
//    public int hashCode() {
//        return Arrays.hashCode(movables);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("Collection of movable objects with size %d:\n", movables.length));
//        for (Movable m: movables) {
//            sb.append(m);
//        }
//        return sb.toString();
//    }
//}
