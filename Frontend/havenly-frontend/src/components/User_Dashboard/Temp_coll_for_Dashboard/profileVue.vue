<template>
    <div class="user-profile">
        <div class="row p-0 m-0">
            <div class="col-md-6 col-sm-12 p-2">
                <div class="profile-box h-auto">
                    <div class="profile-box-data pb-5">
                        <v-img :src="acc_img" class="profile-img" />
                        <form @submit.prevent="submit">


                            <div class="form-control">
                                <div :v-if="user_data !== null">
                                    <input type="text" :value="user_data?.name || ''" :counter="10" label="User name">
                                    <input type="email" :value="user_data?.gmail || ''" label="E-mail">
                                    <input type="phone" :value="user_data?.phone || ''" label="phone">

                                </div>
                            </div>


                            <v-row class="w-100">

                                <v-btn elevation="10" class="submit mx-auto mt-2" type="submit"
                                    style="text-transform:capitalize;">
                                    Save chane
                                </v-btn>


                            </v-row>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-sm-12 p-0 ">

                <div class="row-12 p-2">
                    <div class="profile-box">
                        <div class="profile-box-data">
                            <div class="profile-box-info p-3">
                                <div class="d-flex w-100 p-0 justify-space-between">
                                    <h5>Account info status</h5>
                                    <p
                                        style="color: #fff; padding: 4px 12px; background-color: #4CAF50; border-radius: 17px;">
                                        Ediable<i class="fa-solid fa-check ms-1"></i></p>
                                </div>
                                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Subscribed package</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-store</v-icon>&nbsp;Check
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Reset password</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-lock</v-icon>&nbsp;Reset
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Change location</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-map-marker-radius</v-icon>&nbsp;Change
                                        </span>
                                    </p>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="row-12 p-2">
                    <div class="profile-box">
                        <div class="profile-box-data">
                            <div class="profile-box-info p-3">
                                <div class="d-flex w-100 p-0 justify-space-between">
                                    <h5>Account progress status</h5>
                                    <p
                                        style="color: #fff; padding: 4px 12px; background-color: #4CAF50; border-radius: 17px;">
                                        Ediable<i class="fa-solid fa-check ms-1"></i></p>
                                </div>
                                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Deleted pending Posts</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-delete-clock</v-icon>&nbsp;Check
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Update pending Posts</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-update</v-icon>&nbsp;Check
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Request an event</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-calendar-edit</v-icon>&nbsp;Create
                                        </span>
                                    </p>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>

                <div class="row-12 p-2">
                    <div class="profile-box">
                        <div class="profile-box-data">
                            <div class="profile-box-info p-3">
                                <div class="d-flex w-100 p-0 justify-space-between">
                                    <h5>Account progress status</h5>
                                    <p
                                        style="color: #fff; padding: 4px 12px; background-color: #4CAF50; border-radius: 17px;">
                                        Ediable<i class="fa-solid fa-check ms-1"></i></p>
                                </div>
                                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Deleted pending Posts</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-delete-clock</v-icon>&nbsp;Check
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Update pending Posts</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-update</v-icon>&nbsp;Check
                                        </span>
                                    </p>
                                </div>
                                <div class="d-flex justify-space-between">
                                    <p class="fw-bold">Request an event</p>
                                    <p
                                        style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">

                                        <span class="d-flex align-center">
                                            <v-icon>mdi-calendar-edit</v-icon>&nbsp;Create
                                        </span>
                                    </p>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

</template>

<script>



export default {
    name: 'profileVue',


    data: () => ({

        img: require('@/assets/img/9.jpg'),
        acc_img: require('@/assets/img/img_avatar.png')
    }),

    computed: {
        user_data() {
            if (this.$store.getters.get_LoginedId) {
                return this.$store.getters.Take_Userinfo;
            } else {
                return null;
            }
        }
    },

    methods: {

    }
}

</script>

<style>
.user-profile {
    width: 100%;
    height: 100%;

    .profile-box {
        padding: 8px;
        justify-content: center;
        align-items: center;
        border-radius: 10px;

        box-shadow: 0px 6px 20px 2px rgba(0, 0, 0, 0.4);

        background-color: #FEFCFF;

        .profile-box-data {

            .v-img {
                border-radius: 6px;
            }

            .profile-img {
                width: 200px;
                height: 200px;
                border-radius: 50%;
                margin: auto;
            }

            .form-control {
                width: 100%;
                height: auto;
                padding: 10px 0px;
                display: block;
                background-color: transparent;
                border: none;

                input {
                    width: 100%;
                    background-color: #fff;
                    margin: 10px 0px;
                    font-size: 18px;
                    padding: 10px;
                    border-radius: 10px;
                    border: 1px solid #F6F5F2;
                    box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.4);

                    &:focus {
                        outline: none;
                    }
                }

            }

            .v-btn {
                border-radius: 20px;
                background-color: #E86F52;
                color: #fff;
            }
        }

    }
}
</style>
