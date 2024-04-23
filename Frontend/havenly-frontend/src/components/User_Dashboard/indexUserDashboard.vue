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

                    <a class="sidebar-link" :class="{ active: activeTab === 'profile' }"
                        @click="changeTab('profile'); toggleSidebar2()">
                        <v-icon>mdi-account</v-icon>
                        <span>Profile</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeTab === 'all-post' }"
                        @click="changeTab('all-post'); toggleSidebar2()">
                        <v-icon>mdi-database</v-icon>
                        <span>All post</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeTab === 'saved-post' }"
                        @click="changeTab('saved-post'); toggleSidebar2()">
                        <v-icon>mdi-content-save-all</v-icon>
                        <span>Saved post</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeTab === 'create-post' }"
                        @click="changeTab('create-post'); toggleSidebar2()">
                        <v-icon>mdi-post</v-icon>
                        <span>Create Post</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeTab === 'create-ads' }"
                        @click="changeTab('create-ads'); toggleSidebar2()">
                        <v-icon>mdi-google-ads</v-icon>
                        <span>Create Ads</span>
                    </a>
                </li>


                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeTab === 'notification' }"
                        @click="changeTab('notification'); toggleSidebar2()">
                        <v-icon>mdi-bell-ring</v-icon>
                        <span>Notification</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link" :class="{ active: activeTab === 'settings' }"
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

        <div class="main-data">

            <div class="row">
                <div class="col-md-8 p-0">


                    <div v-if="activeTab === 'profile'">
                        <profile />
                    </div>
                    <div v-else-if="activeTab === 'all-post'">
                        <h3>All Post Content</h3>
                        <p>This is where the add post content will be displayed.</p>
                    </div>
                    <div v-else-if="activeTab === 'saved-post'">
                        <h3>Saved Post Content</h3>
                        <p>This is where the add post content will be displayed.</p>
                    </div>
                    <div v-else-if="activeTab === 'create-post'">
                        <h3>Add Post Content</h3>
                        <p>This is where the add post content will be displayed.</p>
                    </div>
                    <div v-else-if="activeTab === 'create-ads'">
                        <h3>Add Ads Content</h3>
                        <p>This is where the add ads content will be displayed.</p>
                    </div>
                    <div v-else-if="activeTab === 'notification'">
                        <h3>Notification Content</h3>
                        <p>This is where the logout content will be displayed.</p>
                    </div>
                    <div v-else-if="activeTab === 'settings'">
                        <h3>Settings Content</h3>
                        <p>This is where the settings content will be displayed.</p>
                    </div>
                </div>
                <div class="col-md-4 col-sm-0 p-2">

                    <!-- first event announcement row -->
                    <div class="row-12">
                        <event />
                    </div>
                    <v-divider></v-divider>
                    <div class="row-12"
                        style="box-shadow: inset 0px 0px 6px rgba(0, 0, 0, 0.5); border-radius: 12px; padding: 16px 0px;">
                        <ads_medium_page />
                    </div>
                </div>
            </div>

        </div>

    </div>


</template>

<script>
import profile from './Temp_coll_for_Dashboard/profileVue.vue'
import Swal from 'sweetalert2';

// page import 
import event from '../Temp_Collection/eventVue.vue'
import ads_medium_page from '../Temp_Collection/ads-Medium.vue'

export default {
    name: 'indexUserDashboard',
    components: {
        profile,
        event,
        ads_medium_page,
    },
    data() {
        return {
            isExpanded: false,
            activeTab: 'profile', // Default active tab
            isCollapsed: false // W
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
            this.activeTab = tab;
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
.main-data {
    margin-left: auto;
    height: auto;
    width: calc(100% - 70px);
    /* Adjusted to account for the width of the sidebar */
    padding: 6px 20px;
    transition: all 0.35s ease-in-out;
    background-color: #fafbfe;
}

@media only screen and (min-width: 768px) {

    /* for Desktop */
    .main-data {
        margin-top: 4.4%;
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
    border-top-right-radius: 10px;
    border-bottom-right-radius: 10px;
    position: fixed;
    top: 11%;
    left: 0;
    width: 70px;
    min-width: 70px;
    height: 88.9vh;
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