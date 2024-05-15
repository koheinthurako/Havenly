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

                    <a class="sideTextLink" :class="{ active: openTab === 'profile' }"
                        @click="changeTab('profile'); toggleSidebar2()">
                        <v-icon>mdi-account</v-icon>
                        <span>Profile</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sideTextLink" :class="{ active: openTab === 'all-post' }"
                        @click="changeTab('all-post'); toggleSidebar2()">
                        <v-icon>mdi-database</v-icon>
                        <span>All post</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sideTextLink" :class="{ active: openTab === 'create-sell-post' }" 
                        @click="changeTabForSub('create-sell-post'); toggleSidebar2()">
                        <v-icon>mdi-shape-plus</v-icon>
                        <span>Create Sell Post</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sideTextLink" :class="{ active: openTab === 'create-rent-post' }"
                        @click="changeTabForSub('create-rent-post'); toggleSidebar2()">
                        <v-icon>mdi-shape-plus</v-icon>
                        <span>Create Rent Post</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sideTextLink" :class="{ active: openTab === 'create-ads' }"
                        @click="changeTabForSub('create-ads'); toggleSidebar2()">
                        <v-icon>mdi-google-ads</v-icon>
                        <span>Create Ads</span>
                    </a>
                </li>

            </ul>
            <div class="d-flex custom-logout" @click="logout" style="cursor:pointer;">
                <button class="toggle-btn" type="button">
                    <v-icon>mdi-logout</v-icon>
                </button>
                <div class="sidebar-logo">
                    <a class="sideTextLink">Logout</a>
                </div>
            </div>

        </div>





        <div class="main-data px-5 py-4">


            <div class="row">
                <div class="col-md-12 p-0 ">


                    <div v-if="openTab === 'profile'">
                        <profile_page />
                    </div>
                    <div v-else-if="openTab === 'all-post'">
                        <h3>All Post Content</h3>
                        <p>This is where the add post content will be displayed.</p>
                    </div>

                    <div v-else-if="openTab === 'create-sell-post'">

                        <create_sell_post_page />
                    </div>

                    <div v-else-if="openTab === 'create-rent-post'">

                        <create_rent_post_page />
                    </div>
                    <div v-else-if="openTab === 'create-ads'">
                        <h3>Add Ads Content</h3>
                        <p>This is where the add ads content will be displayed.</p>
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

import Swal from 'sweetalert2';

// page import 
import profile_page from './Temp_coll_for_Dashboard/profileVue.vue'
import create_sell_post_page from './Temp_coll_for_Dashboard/create_sell_post.vue'
import create_rent_post_page from './Temp_coll_for_Dashboard/create_rent_post.vue'
import router from '@/router';


export default {
    name: 'indexUserDashboard',

    components: {
        profile_page,
        create_sell_post_page,
        create_rent_post_page
    },

    data() {
        // const storedDialogDash = localStorage.getItem('dialogDash');
        // const dialogDash = storedDialogDash !== null && storedDialogDash !== undefined ? storedDialogDash === 'true' : false;
        return {
            isExpanded: false,  // for left side dashboard collapse and expand
            isCollapsed: false,
            openTab: localStorage.getItem('openTab') || 'profile',
        };
    },
    methods: {


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
            this.openTab = tab;
            localStorage.setItem('openTab', this.openTab);
        },

        changeTabForSub(tab) {
            const checkSubUser = JSON.parse(sessionStorage.getItem('login_user'));
            if(checkSubUser.packageType) {
                this.openTab = tab;
                localStorage.setItem('openTab', this.openTab);
            } else {
                alert("This is for subscriber user only. Please subscribe first")
                router.push('/subscribe');
            }
        },

        closeSidebarOnClickOutside(event) {
            if (!this.$refs.sidebar || !this.$refs.sidebar.contains(event.target)) {
                this.isExpanded = false;
            }
        },


        // for Logout 
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

}


.main-data {
    margin-left: auto;
    height: auto;
    width: calc(100% - 70px);
    padding: 6px 20px;
    transition: all 0.35s ease-in-out;
    background-color: #fff;

}

@media only screen and (min-width: 768px) {

    /* for Desktop */
    .main-data {
        margin-top: 7%;
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
    top: 8%;
    left: 0;
    width: 70px;
    min-width: 70px;
    height: 100vh;
    z-index: 1000;
    transition: all 0.3s ease-in-out;
    padding-top: 43px;
    background-color: #525252;
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
#sidebar:not(.expand) a.sideTextLink span {
    display: none;
}

.sidebar-nav {
    padding: 2rem 0;
    flex: 1 1 auto;

    .sidebar-item {
        position: relative;

        a.sideTextLink {
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

#sidebar.expand .sideTextLink[data-bs-toggle="collapse"]::after {
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

#sidebar.expand .sideTextLink[data-bs-toggle="collapse"].collapsed::after {
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

#sidebar .sidebar-item .sideTextLink {
    background-color: none;
    text-decoration: none;
    color: #fff;
    padding: .625rem 1.625rem;
    display: block;
    font-size: 0.9rem;
    white-space: nowrap;
    border-left: 5px solid transparent;
    text-decoration: none;
    cursor: pointer;
}

</style>