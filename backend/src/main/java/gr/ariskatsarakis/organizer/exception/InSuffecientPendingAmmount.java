package gr.ariskatsarakis.organizer.exception;

import gr.ariskatsarakis.organizer.user.UserInfo;

public class InSuffecientPendingAmmount extends RuntimeException {
        private Long savingGoalId;
        private UserInfo userInfo;

        public InSuffecientPendingAmmount(UserInfo userInfo, Long savingGoalId) {
                super(String.format("user: %s has insuffecient ammount pending", userInfo.getEmail()));
                this.userInfo = userInfo;
                this.savingGoalId = savingGoalId;

        }

        public Long getSavingGoalId() {
                return savingGoalId;
        }

        public void setSavingGoalId(Long savingGoalId) {
                this.savingGoalId = savingGoalId;
        }

        public UserInfo getUserInfo() {
                return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
        }

}
