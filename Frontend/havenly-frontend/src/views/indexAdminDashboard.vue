<template>

    <div class="admin-dashboard">
        <div id="sidebar" ref="sidebar2" :class="{ expand: adminExpanded }">
            <div class="d-flex">
                <button class="toggle-btn" type="button" @click="toggleAdminSidebar" :title="dynamicTitle">
                    <v-icon :hidden="adminExpanded">mdi-view-grid</v-icon>
                    <v-icon :hidden="!adminExpanded">mdi-close</v-icon>
                </button>
                <div class="sidebar-logo">
                    <a href="#">Dashboard</a>
                </div>
            </div>
            <ul class="sidebar-nav ">
                <li class="sidebar-item">

                    <a class="sideTextLink" :class="{ adminActive: adminTab === 'adminProfile' }"
                        @click="changeAdminTab('adminProfile'); toggleAdminSidebar2()">
                        <v-icon>mdi-account</v-icon>
                        <span>Profile</span>
                    </a>
                </li>


                <li class="sidebar-item">
                    <a class="sideTextLink" :class="{ adminActive: adminTab === 'pending-post' }"
                        @click="changeAdminTab('pending-post'); toggleAdminSidebar2()">
                        <v-icon>mdi-database-sync</v-icon>
                        <span>Pending posts</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sideTextLink" :class="{ adminActive: adminTab === 'pending-ad' }"
                        @click="changeAdminTab('pending-ad'); toggleAdminSidebar2()">
                        <v-icon>mdi-database-sync-outline</v-icon>
                        <span>Pending Ads</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sideTextLink" :class="{ adminActive: adminTab === 'register-user' }"
                        @click="changeAdminTab('register-user'); toggleAdminSidebar2()">
                        <v-icon>mdi-account-group</v-icon>
                        <span>Register users</span>
                    </a>
                </li>

                <li class="sidebar-item">
                    <a class="sideTextLink" :class="{ adminActive: adminTab === 'ban-user' }"
                        @click="changeAdminTab('ban-user'); toggleAdminSidebar2()">
                        <v-icon>mdi-account-off</v-icon>
                        <span>Ban users</span>
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

        <div class="main-data">


            <div class="row">
                <div class="col-md-12 p-0 ">

                    <div v-if="adminTab === 'adminProfile'">
                        <!-- <profile_page /> -->
                        <AdminProfile />
                    </div>

                    <div v-else-if="adminTab === 'pending-post'">
                        <!-- <h3>All Post Content</h3>
                        <p>This is where the add post content will be displayed.</p> -->
                        <!-- <interestedPosts /> -->
                        <AdminPost />
                    </div>

                    <div v-else-if="adminTab === 'pending-ad'">
                        <AdminAd />
                    </div>

                    <div v-else-if="adminTab === 'register-user'">
                        <!-- <uploadedAllPosts /> -->
                        <AdminView />
                    </div>

                    <div v-else-if="adminTab === 'ban-user'">
                        <!-- <create_sell_post_page /> -->
                        <AdminBanList />
                    </div>

                </div>

            </div>

        </div>

    </div>

</template>

<script>

import Swal from 'sweetalert2';

// page import 
import AdminView from '@/views/adminDashboardCategories/AdminView.vue'
import AdminPost from '@/views/adminDashboardCategories/AdminPost.vue'
import AdminBanList from '@/views/adminDashboardCategories/AdminBanList.vue'
import AdminProfile from '@/views/adminDashboardCategories/adminProfile.vue'
import AdminAd from './adminDashboardCategories/AdminAd.vue';
// import router from '@/router';


export default {
    name: 'indexUserDashboard',

    components: {
        AdminView,
        AdminPost,
        AdminAd,
        AdminBanList,
        AdminProfile
    },

    data() {
        return {
            adminExpanded: false,  // for left side dashboard collapse and expand
            adminTab: sessionStorage.getItem('adminTab') || 'adminProfile',

        };
    },

    mounted() {

        sessionStorage.removeItem('adminTab');
        if (this.adminTab === "adminProfile") {
            this.adminExpanded = true;
        }
    },

    watch: {
        '$route'(to, from) {
            console.log("Route change detected!");
            console.log("From:", from.name, "To:", to.name);
        }
    },

    // beforeUnmount() {
    //   window.removeEventListener('click', this.closeOnClickOutside);
    // },

    computed: {
        dynamicTitle() {
            return this.adminExpanded ? 'Close Sidebar' : 'Open Sidebar';
        }
    },


    methods: {

        toggleAdminSidebar() {
            this.adminExpanded = !this.adminExpanded;
            if (this.adminExpanded) {
                window.addEventListener('click', this.closeOnClickOutside);
            } else {
                window.removeEventListener('click', this.closeOnClickOutside);
            }
        },

        toggleAdminSidebar2() {
            this.adminExpanded = false;
        },

        changeAdminTab(tab) {
            this.adminTab = tab;
            sessionStorage.setItem('adminTab', this.adminTab);
        },

        // changeTabForSub(tab) {
        //   const checkSubUser = JSON.parse(localStorage.getItem('sub_user'));
        //   const packageType = checkSubUser.packageType;
        //   console.log(packageType);
        //   if (packageType) {
        //     this.adminTab = tab;
        //     localStorage.setItem('adminTab', this.adminTab);
        //   } else {
        //     Swal.fire({
        //       title: 'Need Subscription!',
        //       text: 'This is for subscriber user only. Please subscribe first.',
        //       icon: 'info',
        //       customClass: {
        //         confirmButton: 'myCustomButton'
        //       },
        //       buttonsStyling: false,
        //       allowOutsideClick: false,
        //       allowEscapeKey: false
        //     }).then(() => {
        //       router.push('/subscribe');
        //     });
        //   }
        // },

        closeOnClickOutside(event) {
            if (!this.$refs.sidebar2 || !this.$refs.sidebar2.contains(event.target)) {
                this.adminExpanded = false;
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
                    sessionStorage.removeItem('admin_user');
                    this.$router.push('/admin/login');
                }
            });
        }

    },
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
    top: 0;
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
        transition: all 0.3s ease-in-out;

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
    transition: 0.2s;
    cursor: pointer;
}


#sidebar .sidebar-item .sideTextLink:hover {
    background-color: #e86f52;
}

.adminActive {
    background-color: #e86f52;

    .v-icon {
        color: #525252;
    }
}
</style>