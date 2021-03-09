public enum CalibrationValues {

        PLATFORM_HORIZONTAL(-30), WHITE_CUP_HORIZONTAL(-330), BLACK_CUP_HORIZONTAL(-600),
        PLATFORM_VERT(180), MOVE_HEIGHT_VERT(0), CUP_VERT(150),
        MOVEMENT_SPEED(450), MOVEMENT_ACCELERATION(3000),
        CLAW_OPEN(-75);

        private int value;

        CalibrationValues(int value) {
            this.value = value;
        }

        public int getValue()
        {
            return this.value;
        }
    }

