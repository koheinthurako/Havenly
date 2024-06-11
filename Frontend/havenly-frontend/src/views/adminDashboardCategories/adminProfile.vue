<template>
    <div class="admin-profile">
        <div class="col-md-6 col-sm-12 p-2 mx-auto">
            <div class="profile-box h-auto">
                <div class="profile-box-data pb-5">
                    <v-img :src="acc_img" class="profile-img mx-auto mt-2" />
                    <form ref="form" fast-fail @submit.prevent="update">
                        <div class="mt-3 p-3 mx-auto">

                            <!-- <input type="text" :value="user_data?.name || ''" label="User name">
                                <input type="email" :value="user_data?.gmail || ''" label="E-mail">
                                <input type="phone" :value="user_data?.phone || ''" label="phone"> -->

                            <!-- <v-text-field variant="solo" density="comfortable" clear-icon="mdi-close-circle" clearable
                                rounded="lg" v-model="getAdminData.name" :rules="[validateName]"
                                label="Username"></v-text-field> -->

                            <!-- <v-text-field class="mt-2" variant="solo" density="comfortable"
                                clear-icon="mdi-close-circle" clearable rounded="lg" v-model="getAdminData.phone"
                                :rules="[validatePhone]" label="Phone no."></v-text-field> -->

                            <v-text-field class="mt-2" variant="solo" density="comfortable"
                                clear-icon="mdi-close-circle" clearable rounded="lg" v-model="getAdminData.email"
                                label="Email of this account" :rules="[validateGmail]"></v-text-field>

                            <v-row class="w-100 mt-3">
                                <v-btn elevation="10" class="submit mx-auto mt-2" type="submit"
                                    style="text-transform:capitalize;">
                                    Update
                                </v-btn>
                            </v-row>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {

    data: () => ({
        acc_img: require('@/assets/img/img_avatar.png'),

        getAdminData: [],
    }),

    methods: {
        // User name validation
        validateName(value) {
            if (!value) {
                return 'Required';
            } else if (value.length < 5) {
                return 'Name must be at least 5 characters long';
            }
            return true;
        },
    },

    mounted() {
        this.getAdminData = JSON.parse(sessionStorage.getItem('admin_user'));
    }


}

</script>


<style lang="scss">
.admin-profile {
    width: 70%;
    height: 100%;
    margin: auto;

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
                width: 130px;
                height: 130px;
                border-radius: 50%;
                margin: left;
                margin-left: 10px;
            }



            .form-control {
                width: 100%;
                height: auto;
                background-color: transparent;
                border: none;




            }

            .v-btn {
                border-radius: 20px;
                background-color: #E86F52;
                color: #fff;
            }





        }



    }



    .switch {
        position: relative;
        display: inline-block;
        width: 60px;
        height: 34px;
    }

    .switch input {
        opacity: 0;
        width: 0;
        height: 0;
    }

    .slider {
        position: absolute;
        cursor: pointer;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: #ccc;
        -webkit-transition: .4s;
        transition: .4s;
    }

    .slider:before {
        position: absolute;
        content: "";
        height: 26px;
        width: 26px;
        left: 4px;
        bottom: 4px;
        background-color: #fff;
        -webkit-transition: .4s;
        transition: .4s;
        box-shadow: 0px 4px 20px 1px rgba(0, 0, 0, 0.4);
    }

    input:checked+.slider {
        background-color: #E97559;
    }

    input:focus+.slider {
        box-shadow: 0 0 1px #E97559;
    }

    input:checked+.slider:before {
        -webkit-transform: translateX(26px);
        -ms-transform: translateX(26px);
        transform: translateX(26px);
    }

    .slider.round {
        border-radius: 34px;
    }

    .slider.round:before {
        border-radius: 50%;
    }
}
</style>