<template>
    <div class="user-dashboard">
        <div id="sidebar" ref="sidebar" :class="{ expand: isExpanded }">
            <div class="d-flex">
                <button class="toggle-btn" type="button" @click="toggleSidebar">
                    <v-icon>mdi-view-grid</v-icon>
                </button>
                <div class="sidebar-logo">
                    <a href="#">Dashboard</a>
                </div>
            </div>
            <ul class="sidebar-nav ">
                <li class="sidebar-item">

                    <a class="sidebar-link" :class="{ active: activeDash === 'profile' }"
                        @click="changeTab('profile'); toggleSidebar2()">
                        <v-icon>mdi-account</v-icon>
                        <span>Profile</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeDash === 'all-post' }"
                        @click="changeTab('all-post'); toggleSidebar2()">
                        <v-icon>mdi-database</v-icon>
                        <span>All post</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeDash === 'saved-post' }"
                        @click="changeTab('saved-post'); toggleSidebar2()">
                        <v-icon>mdi-content-save-all</v-icon>
                        <span>Saved post</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeDash === 'create-post' }"
                        @click="openDialog('create-post'); toggleSidebar2()">
                        <v-icon>mdi-shape-plus</v-icon>
                        <span>Create Post</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeDash === 'create-ads' }"
                        @click="changeTab('create-ads'); toggleSidebar2()">
                        <v-icon>mdi-google-ads</v-icon>
                        <span>Create Ads</span>
                    </a>
                </li>


                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeDash === 'notification' }"
                        @click="changeTab('notification'); toggleSidebar2()">
                        <v-icon>mdi-bell-ring</v-icon>
                        <span>Notification</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeDash === 'settings' }"
                        @click="changeTab('settings'); toggleSidebar2()">
                        <v-icon>mdi-cog</v-icon>
                        <span>Setting</span>
                    </a>
                </li>



            </ul>
            <div class="d-flex custom-logout" @click="logout" style="cursor:pointer;">
                <button class="toggle-btn" type="button">
                    <v-icon>mdi-logout</v-icon>
                </button>
                <div class="sidebar-logo">
                    <a class="sidebar-link">Logout</a>
                </div>
            </div>

        </div>



        <!-- dialog start -->
        <v-dialog v-model="dialogDash" class="create-pop-up" persistent>

            <!-- main-close-btn-start -->
            <v-btn class="main-close" @click="changeTab('profile'); toggleSidebar2(); closeDialog()">
                <v-icon>mdi-close-circle-outline</v-icon>
            </v-btn>
            <!-- main-close-btn-end -->

            <!-- swiper start -->
            <swiper :keyboard="true" :loop="false" :effect="'coverflow'" :grabCursor="true" :centeredSlides="true"
                :slidesPerView="'auto'" :navigation="true" :mousewheel="true" :coverflowEffect="{
                    rotate: 10,
                    stretch: 0,
                    depth: 30,
                    modifier: 2.5,
                    slideShadows: true,
                }" :modules="modules" class="mySwiper">

                <!-- package one start -->
                <swiper-slide>
                    <div class="temp-package">
                        <div style="line-height: 0px;">
                            <h3 class="d-flex">
                                Create Post for sell
                            </h3>
                            <span class="text-grey mt-1">Read some docs.</span>
                        </div>


                        <div class="w-100 mt-5 px-4">

                            <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p1">Over
                                    all 3
                                    posts.</p>
                            </div>
                            <div class="d-flex"><v-icon class="icon-2">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p2">Over
                                    all 3 ads.
                                </p>
                            </div>
                            <div class="d-flex"><v-icon class="icon-3">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p3">3
                                    images per
                                    post.</p>
                            </div>
                            <div class="d-flex"><v-icon class="icon-4">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p4">1000
                                    words per
                                    post.</p>
                            </div>

                        </div>
                        <div class="w-100 mt-5 d-flex justify-center temp-btn">
                            <v-btn elevation="20" class="sub-btn"
                                @click="changeTab('create-post-real'); toggleSidebar2(); closeDialog()">
                                <div class="toggle-v-l">
                                    <span class="visible">Create</span>
                                    <span class="later-visible">Sale Post</span>
                                </div>
                            </v-btn>
                        </div>
                    </div>
                </swiper-slide>
                <!-- package one end -->

                <!-- package two start -->
                <swiper-slide>
                    <div class="temp-package">
                        <div style="line-height: 0px;">
                            <h3 class="d-flex">
                                Create post for Rent
                            </h3>
                            <span class="text-grey mt-1">Read some docs.</span>
                        </div>


                        <div class="w-100 mt-5 px-4">

                            <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p1">Over
                                    all 3
                                    posts.</p>
                            </div>
                            <div class="d-flex"><v-icon class="icon-2">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p2">Over
                                    all 3 ads.
                                </p>
                            </div>
                            <div class="d-flex"><v-icon class="icon-3">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p3">3
                                    images per
                                    post.</p>
                            </div>
                            <div class="d-flex"><v-icon class="icon-4">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p4">1000
                                    words per
                                    post.</p>
                            </div>

                        </div>
                        <div class="w-100 mt-5 d-flex justify-center temp-btn">
                            <v-btn elevation="20" class="sub-btn"
                                @click="changeTab('create-post-real'); toggleSidebar2(); closeDialog()">
                                <div class="toggle-v-l">
                                    <span class="visible">Create</span>
                                    <span class="later-visible">Rent Post</span>
                                </div>
                            </v-btn>
                        </div>
                    </div>
                </swiper-slide>
                <!-- package two end -->

                <!-- package three start -->
                <swiper-slide>
                    <div class="temp-package">
                        <div style="line-height: 0px;">
                            <h3 class="d-flex">
                                Create post for Hostel
                            </h3>
                            <span class="text-grey mt-1">Read some docs.</span>
                        </div>


                        <div class="w-100 mt-5 px-4">

                            <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p1">Over
                                    all 3
                                    posts.</p>
                            </div>
                            <div class="d-flex"><v-icon class="icon-2">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p2">Over
                                    all 3 ads.
                                </p>
                            </div>
                            <div class="d-flex"><v-icon class="icon-3">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p3">3
                                    images per
                                    post.</p>
                            </div>
                            <div class="d-flex"><v-icon class="icon-4">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                    class="p4">1000
                                    words per
                                    post.</p>
                            </div>

                        </div>
                        <div class="w-100 mt-5 d-flex justify-center temp-btn">
                            <v-btn elevation="20" class="sub-btn"
                                @click="changeTab('create-post-real'); toggleSidebar2(); closeDialog()">
                                <div class="toggle-v-l">
                                    <span class="visible">Create</span>
                                    <span class="later-visible">Hostel Post</span>
                                </div>
                            </v-btn>
                        </div>
                    </div>
                </swiper-slide>
                <!-- package three end -->




            </swiper>
            <!-- swiper end -->
        </v-dialog>
        <!-- dialog end -->

        <div class="main-data">


            <div class="row">
                <div class="col-md-7 p-0 mx-auto">


                    <div v-if="openTab === 'profile'">
                        <profile />
                    </div>
                    <div v-else-if="openTab === 'all-post'">
                        <h3>All Post Content</h3>
                        <p>This is where the add post content will be displayed.</p>
                    </div>
                    <div v-else-if="openTab === 'saved-post'">
                        <h3>Saved Post Content</h3>
                        <p>This is where the add post content will be displayed.</p>
                    </div>
                    <div v-else-if="openTab === 'create-post'">
                        <div style="width: 100%; height: 130vh;"></div>

                    </div>
                    <div v-else-if="openTab === 'create-post-real'">
                        <create_post_page />
                    </div>
                    <div v-else-if="openTab === 'create-ads'">
                        <h3>Add Ads Content</h3>
                        <p>This is where the add ads content will be displayed.</p>
                    </div>
                    <div v-else-if="openTab === 'notification'">
                        <h3>Notification Content</h3>
                        <p>This is where the logout content will be displayed.</p>
                    </div>
                    <div v-else-if="openTab === 'settings'">
                        <h3>Settings Content</h3>
                        <p>This is where the settings content will be displayed.</p>
                    </div>
                </div>

                <!-- <div class="col-md-4 col-sm-0 p-2">
    
                        <div class="row-12">
                            <event />
                        </div>
                        <v-divider></v-divider>
                        <div class="row-12"
                            style="box-shadow: inset 0px 0px 6px rgba(0, 0, 0, 0.5); border-radius: 12px; padding: 16px 0px;">
                            <ads_medium_page />
                        </div>
                    </div> -->

            </div>

        </div>

    </div>


</template>

<script>
import profile from './Temp_coll_for_Dashboard/profileVue.vue'

import create_post_page from './Temp_coll_for_Dashboard/create_post.vue'


import Swal from 'sweetalert2';

import { Swiper, SwiperSlide } from 'swiper/vue';

import 'swiper/css';

import 'swiper/css/effect-coverflow';
import 'swiper/css/pagination';

import { EffectCoverflow, Pagination, Mousewheel, Keyboard } from 'swiper/modules';

export default {
    name: 'indexUserDashboard',
    components: {
        profile,
        create_post_page,

        // for Swiper 
        Swiper,
        SwiperSlide,
    },

    setup() {
        return {
            modules: [EffectCoverflow, Pagination, Mousewheel, Keyboard],
        };
    },

    data() {
        const storedDialogDash = localStorage.getItem('dialogDash');
        const dialogDash = storedDialogDash !== null && storedDialogDash !== undefined ? storedDialogDash === 'true' : false;
        return {
            isExpanded: false,
            isCollapsed: false,
            dialogDash: dialogDash,
            activeDash: localStorage.getItem('activeDash') || 'profile',
            openTab: localStorage.getItem('openTab') || 'profile',
        };
    },
    methods: {
        openDialog(tab) {

            if (tab === 'create-post-real') {
                this.activeDash = 'create-post';
                this.openTab = 'create-post-real';
                this.dialogDash = true;
            } else {
                this.activeDash = tab;
                this.openTab = tab;
                this.dialogDash = true;
            }
            localStorage.setItem('dialogDash', this.dialogDash);
            localStorage.setItem('openTab', tab);
            localStorage.setItem('activeDash', tab);
        },
        closeDialog() {
            this.dialogDash = false;
            localStorage.setItem('dialogDash', false);
        },

        toggleSidebar() {
            this.isExpanded = !this.isExpanded;
            if (this.isExpanded) {
                window.addEventListener('click', this.closeSidebarOnClickOutside);
            } else {
                window.removeEventListener('click', this.closeSidebarOnClickOutside);
            }
        },

        toggleSidebar2() {
            this.isExpanded = false;
        },

        changeTab(tab) {
            if (tab === 'create-post-real') {
                this.activeDash = 'create-post';
                this.openTab = 'create-post-real';
            } else {
                this.activeDash = tab;
                this.openTab = tab;
            }
            localStorage.setItem('activeDash', this.activeDash);
            localStorage.setItem('openTab', this.openTab);

        },

        closeSidebarOnClickOutside(event) {
            if (!this.$refs.sidebar || !this.$refs.sidebar.contains(event.target)) {
                this.isExpanded = false;
            }
        },
        logout() {
            Swal.fire({
                title: 'Be careful!',
                text: "Are you sure, you want to logout!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Logout',
                cancelButtonText: 'Cancel',
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33'
            }).then((result) => {
                if (result.isConfirmed) {
                    this.$store.dispatch('To_Logout_Action');
                    this.$router.push('/home');
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    this.$router.push('/userdashboard');
                }
                else {
                    this.$router.push('/userdashboard'); // Redirect to dashboard after timeout
                }
            });
        }
    },
    mounted() {
        window.addEventListener('click', this.closeSidebarOnClickOutside);
    },
    beforeUnmount() {
        window.removeEventListener('click', this.closeSidebarOnClickOutside);
    }
}
</script>

<style scoped>
.create-pop-up {
    width: 100%;
    height: auto;
    z-index: 100;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .main-close {
        position: absolute;
        top: -50px;
        right: 0;
        padding: 0;
        z-index: 300;
        width: 30px !important;
        height: 62px;
        border-radius: 50%;
        background-color: #e86f52;
        color: #fff;
        box-shadow: 0px 6px 20px -2px rgba(0, 0, 0, 0.3);

        .v-icon {
            font-size: 24px;
        }
    }

    .swiper {
        width: 90%;
        overflow: hidden;

        .swiper-slide {
            background-position: center;
            background-size: cover;
            width: 340px;
            height: 400px;
            filter: blur(3px);
            transition: all 0.4s ease-in-out;
            overflow: hidden;

            .temp-package {
                background-color: #fff;
                width: 100%;
                height: 100%;
                border-radius: 18px;
                box-shadow: inset 0px 0px 7px rgba(0, 0, 0, 0.6);
                padding: 40px 20px;

                .v-icon {
                    color: #e86f52;
                }

                h3 {
                    color: #525252;
                }

                h1 {
                    span {
                        color: #525252;
                    }
                }

                .temp-btn {
                    position: relative;
                    overflow: hidden;

                    .sub-btn {
                        width: 80%;
                        padding: 20px 0px;
                        display: inline-flex;
                        border-radius: 18px;
                        text-transform: capitalize;
                        background-color: #525252;
                        color: #fff;
                        cursor: default;

                        .toggle-v-l {
                            display: inline-block;
                            height: 100%;
                            width: 100%;
                            display: flex;
                            justify-content: center;

                            .visible,
                            .later-visible {
                                transition: all 0.3s ease-in-out;
                            }

                            .later-visible {
                                opacity: 0;
                                position: absolute;
                                bottom: -100px;
                                color: #202842;
                            }
                        }
                    }
                }
            }
        }

        .swiper-slide-active {
            filter: blur(0px);

            .temp-package {
                background-color: #202842;
                color: #fff;

                .temp-btn {
                    .sub-btn {
                        cursor: pointer;
                    }
                }

                .v-icon {
                    color: rgb(12, 218, 12);
                }

                h3 {


                    color: #fff;
                }

                h1 {
                    animation: moveLeftAndBack 0.3s both;
                    color: #e86f52;
                }

                .p1 {
                    animation: moveRightAndBack 0.2s both;
                }

                .icon-1 {
                    animation: zoomin 0.22s both;
                }

                .p2 {
                    animation: moveRightAndBack 0.3s both;
                }

                .icon-2 {
                    animation: zoomin 0.32s both;
                }

                .p3 {
                    animation: moveRightAndBack 0.4s both;
                }

                .icon-3 {
                    animation: zoomin 0.42s both;
                }

                .p4 {
                    animation: moveRightAndBack 0.5s both;
                }

                .icon-4 {
                    animation: zoomin 0.52s both;
                }

                .temp-btn {
                    position: relative;

                    .sub-btn {
                        background-color: #e86f52;
                    }

                    &:hover .visible {
                        color: #202842;
                        opacity: 0;
                        transform: translateY(-50px);
                    }

                    &:hover .later-visible {
                        opacity: 1 !important;
                        bottom: 0px !important;
                        color: #fff !important;
                    }
                }
            }
        }
    }
}


.main-data {
    margin-left: auto;
    height: auto;
    width: calc(100% - 70px);
    /* Adjusted to account for the width of the sidebar */
    padding: 6px 20px;
    transition: all 0.35s ease-in-out;
    background-color: #fff;
}

@media only screen and (min-width: 768px) {

    /* for Desktop */
    .main-data {
        margin-top: 5%;
    }
}


@media only screen and (max-width: 767px) {

    /* for mobile */
    .main-data {
        margin-top: 11%;
    }
}

#sidebar {
    overflow: hidden;
    position: fixed;
    top: 10%;
    left: 0;
    width: 70px;
    min-width: 70px;
    height: 90vh;
    z-index: 1000;
    transition: all 0.3s ease-in-out;
    background-color: #0e2238;
    display: flex;
    flex-direction: column;

    .toggle-btn {
        background-color: transparent;
        cursor: pointer;
        border: 0;
        padding: 1rem 1.5rem;

        .v-icon {
            font-size: 1.5rem;
            color: #FFF;
        }
    }
}

#sidebar.expand {
    width: 260px;
    min-width: 260px;
}


.sidebar-logo {
    margin: auto 0;
}

.sidebar-logo a {
    color: #FFF;
    font-size: 1.15rem;
    font-weight: 600;
    text-decoration: none;
}

#sidebar:not(.expand) .sidebar-logo,
#sidebar:not(.expand) a.sidebar-link span {
    display: none;
}

.sidebar-nav {
    padding: 2rem 0;
    flex: 1 1 auto;

    .sidebar-item {
        position: relative;

        a.sidebar-link {
            padding: .625rem 1.625rem;
            color: #FFF;
            display: block;
            font-size: 0.9rem;
            white-space: nowrap;
            border-left: 5px solid transparent;
            text-decoration: none;
            cursor: pointer;

            .v-icon {
                font-size: 1.3rem;
                margin-right: .75rem;
                margin-left: -6px;
            }

            &:hover {
                background-color: #525252;
                border-left: 5px solid #3b7ddd;
            }
        }

    }

}

.custom-logout {
    border-left: 5px solid transparent;

    .v-icon {
        margin-left: -6px;
    }

    &:hover {
        background-color: #525252;
        border-left: 5px solid #3b7ddd;
    }
}


#sidebar:not(.expand) .sidebar-item .sidebar-dropdown {
    position: absolute;
    top: 0;
    background-color: #0e2238;
    padding: 0;
    min-width: 15rem;
    display: none;
}

#sidebar:not(.expand) .sidebar-item:hover .has-dropdown+.sidebar-dropdown {
    display: block;
    max-height: 100vh;
    width: 100%;
    opacity: 1;
}

#sidebar.expand .sidebar-link[data-bs-toggle="collapse"]::after {
    border: solid;
    border-width: 0 .075rem .075rem 0;
    content: "";
    display: inline-block;
    padding: 2px;
    position: absolute;
    right: 1.5rem;
    top: 1.4rem;
    transform: rotate(-135deg);
    transition: all .3s ease-out;
}

#sidebar.expand .sidebar-link[data-bs-toggle="collapse"].collapsed::after {
    transform: rotate(45deg);
    transition: all .3s ease-out;
}

.button-box {
    width: 100%;
    border-radius: 10px;
    box-shadow: 0px 10px 24px 1px rgba(0, 0, 0, 0.42);

    .custom-link {
        color: #000;
        text-decoration: none;
        position: relative;

        .custom-icon {
            position: absolute;
            margin-top: 1px;
            transition: all 0.3s ease-in-out;
        }
    }

    .custom-link:hover .custom-icon {
        margin-left: 16px;
    }

    .v-icon {
        color: #525252;
    }
}
</style>