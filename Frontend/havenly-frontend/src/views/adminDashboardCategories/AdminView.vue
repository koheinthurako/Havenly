<template>



  <div class="container">
    <h2 class="mb-3" style="color: #e86f52;">All user list</h2>
    <!-- <div class="box1">
      <div id="sidebar" ref="sidebar" :class="{ expand: isExpanded }">
        <div class="d-flex">


          <v-icon>mdi-view-grid</v-icon>


          <ul class="sidebar-nav ">
            <li class="sidebar-item">
              <a class="sidebar-link" @click="pushhh()">
                <span>Pending Posts</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" @click="pushy()">
                <span>Users</span>
              </a>
            </li>
            <li class="sidebar-item">
              <a class="sidebar-link" @click="ban()">

                <span>Ban List</span>
              </a>
            </li>



          </ul>

        </div>

        <div class="d-flex custom-logout" @click="logout" style="cursor:pointer;">
          <button class="toggle-btn" type="button">
            <v-icon>mdi-logout</v-icon>
          </button>
          <div class="sidebar-logo">
            <a class="sidebar-link">&nbsp; &nbsp; &nbsp; &nbsp;Logout</a>
          </div>
        </div>

      </div>
    </div> -->

    <div class="box2">

      <!-- new table start -->

      <!-- <v-card flat class="w-100">
        <div class="d-flex mb-1 justify-content-end">

          <div class="w-25">
            <v-text-field v-model="search" label="Search by name" prepend-inner-icon="mdi-magnify" variant="outlined"
              hide-details single-line></v-text-field>
          </div>
          <div class="w-75">

            <v-radio-group v-model="radios" inline class="d-flex justify-content-end">
              <v-radio label="All user" value="alluser"></v-radio>
              <v-radio label="Normal user" value="reguser"></v-radio>
              <v-radio label="Subscribe user" value="subuser"></v-radio>
            </v-radio-group>
          </div>
        </div>
        <v-data-table :headers="headers" :items="indexedUsers" :search="search">
          <template v-slot:item="{ item }">
            <tr>
              <td class="td">{{ item.index }}</td>
              <td class="td">{{ item.name }}</td>
              <td class="td">{{ item.email }}</td>
              <td class="td">{{ item.phone }}</td>
              <td class="td">{{ item.date }}</td>
              <td class="td">
                <v-btn @click="deleteUser(item.id)" color="#e86f52" class="me-2">Delete</v-btn>
                <v-btn @click="banUser(item)" color="#e86f52">Ban</v-btn>
              </td>
            </tr>
          </template>
</v-data-table>
</v-card> -->

      <v-card flat class="w-100">
        <!-- Search and Radio buttons -->
        <div class="d-flex mb-1 justify-content-end">
          <div class="w-25">
            <v-text-field v-model="search" label="Search by name" prepend-inner-icon="mdi-magnify" variant="outlined"
              hide-details single-line></v-text-field>
          </div>
          <div class="w-75">
            <v-radio-group v-model="radios" inline class="d-flex justify-content-end">
              <v-radio label="All user" value="alluser"></v-radio>
              <v-radio label="Normal user" value="reguser"></v-radio>
              <v-radio label="Subscribe user" value="subuser"></v-radio>
            </v-radio-group>
          </div>
        </div>

        <!-- Data Table -->
        <v-data-table v-if="!loading" :headers="headers" :items="indexedUsers" :search="search">
          <template v-slot:item="{ item }">
            <tr>
              <td class="td">{{ item.index }}</td>
              <td class="td">{{ item.name }}</td>
              <td class="td">{{ item.email }}</td>
              <td class="td">{{ item.phone }}</td>
              <td class="td">{{ item.date }}</td>
              <td class="td">
                <v-btn @click="deleteUser(item.email)" color="#e86f52" class="me-2">Delete</v-btn>
                <v-btn @click="banUser(item)" color="#e86f52">Ban</v-btn>
              </td>
              <td class="td">{{ item.banned ? 'Yes' : 'No' }}</td>
              <!-- <td class="td">
                <v-icon v-if="item.banned">mdi-cancel</v-icon>
              </td> -->
            </tr>
          </template>
        </v-data-table>

        <!-- Loading Indicator -->
        <v-col cols="12" md="12" v-else>
          <v-skeleton-loader class="w-100" type="heading"></v-skeleton-loader>
          <v-skeleton-loader class="w-100" type="table-heading"></v-skeleton-loader>

          <v-skeleton-loader class="w-100" type="table-tfoot"></v-skeleton-loader>

        </v-col>
      </v-card>

      <!-- new table end -->



      <!-- <v-table>
        <thead>
          <tr>
            <th class="text-left">
              Name
            </th>
            <th class="text-left">
              E-mail
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="haha in users" :key="haha.name">
            <td>{{ haha.name }}</td>
            <td>{{ haha.email }}</td>
            <td>

              <v-btn class="mt-2 me-3 bg-redbrick text-white" @click="deleteUser(haha.email)">Delete</v-btn>
              <v-btn class="mt-2 bg-redbrick text-white" @click="banUser(haha)">Ban</v-btn>
            </td>
          </tr>
        </tbody>
      </v-table> -->
    </div>



  </div>
</template>


<script>
import router from '@/router';
import axios from 'axios';
import Swal from 'sweetalert2';

export default {

  data() {
    return {
      users: [
        {
          id: '',
          name: '',
          email: '',
          phone: '',
          data: ''
        },
      ],

      banUsers: [
        {
          email: '',

        },
      ],

      subScribedUsers: [],

      loading: false,
      radios: 'alluser',
      search: '',
      headers: [
        { align: 'start', key: 'index', sortable: false, title: 'No.' },
        { align: 'start', key: 'name', sortable: false, title: 'Name' },
        { key: 'email', title: 'Email', sortable: false, },
        { key: 'phone', title: 'Phone', sortable: false, },
        { key: 'date', title: 'Date', sortable: false, },
        { key: 'action', title: 'Action', sortable: false },
        { key: 'status', title: 'Banned', sortable: false, },
      ],
    }
  },

  mounted() {
    localStorage.removeItem('openTab');

    // get all register data
    this.fetchUsers();

    // get Banned users
    this.fetchBanUsers();

    // get Subscribed users
    this.fetchSubscribedUsers();

  },

  computed: {
    // indexedUsers() {
    //   return this.users.map((user, index) => ({
    //     ...user,
    //     index: index + 1,
    //     banned: this.banUsers.some(banUser => banUser.email === user.email),
    //   }));
    // }

    indexedUsers() {
      let filteredUsers = this.users;

      if (this.radios === 'reguser') {
        // Filter users that are not in the subScribedUsers list
        filteredUsers = this.users.filter(user => !this.subScribedUsers.some(subUser => subUser.sub_id === user.id));
      } else if (this.radios === 'subuser') {
        // Filter users that are in the subScribedUsers list
        filteredUsers = this.users.filter(user => this.subScribedUsers.some(subUser => subUser.sub_id === user.id));
      }

      // Add index and banned status to each user
      return filteredUsers.map((user, index) => ({
        ...user,
        index: index + 1,
        banned: this.banUsers.some(banUser => banUser.email === user.email),
      }));
    }
  },

  methods: {

    async fetchSubscribedUsers() {

      try {
        const response = await axios.get("http://localhost:8083/subscribe/getAll");
        if (response.status === 204) {
          console.log('No data available for this post.');
        } else {
          this.subScribedUsers = response.data.map(item => {
            return {
              sub_id: item.reg_user.register_id,
            };

          });
          console.log("Subscribed Users:", this.subScribedUsers);
        }
      } catch (error) {
        console.error('Error fetching posts:', error);
      }
    },


    async fetchUsers() {
      try {
        this.loading = true; // Set loading state to true before making the request

        const response = await axios.get('http://localhost:8083/getAll');
        const data = response.data;
        const mappedData = data.map(users => ({
          id: users.register_id,
          name: users.name,
          email: users.email,
          phone: users.phone,
          date: users.date,
        }));
        sessionStorage.setItem('users', JSON.stringify(mappedData));
        this.users = mappedData;

        // Set loading state to false after request is completed
        this.loading = false;

        console.log("this users : ", this.users);
      } catch (error) {
        console.error('Error fetching users:', error);
        // In case of error, still set loading state to false
        this.loading = false;
      }
    },

    async fetchBanUsers() {
      this.loading = true; // Start loading
      try {
        const response = await axios.get('http://localhost:8083/ban/getAll');
        const data = response.data;
        const mappedData = data.map(user => ({
          email: user.email
        }));
        sessionStorage.setItem('users', JSON.stringify(mappedData));
        this.banUsers = mappedData;
      } catch (error) {
        console.error('Error fetching users:', error);
      } finally {
        this.loading = false; // Stop loading
      }
    },

    deleteUser(email) {
      Swal.fire({
        title: 'Are you sure?',
        text: 'This account will Delete!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          axios.delete(`http://localhost:8083/delete/${email}`)
            .then(() => {

              this.users = this.users.filter((u) => u.email !== email);
              alert('User deleted successfully.');
            })
            .catch((error) => {
              console.error('Error deleting user:', error);
              alert('Failed to delete user. Please try again.');
            });
        }
      });

    },


    // deleteUser(email) {
    //   axios.delete(`http://localhost:8083/delete/${email}`)
    //     .then(() => {

    //       this.users = this.users.filter((u) => u.email !== email);
    //       alert('User deleted successfully.');
    //     })
    //     .catch((error) => {
    //       console.error('Error deleting user:', error);
    //       alert('Failed to delete user. Please try again.');
    //     });
    // },

    async banUser(user) {
      console.log("inside Banuser : ", user);
      try {
        // Check if the user is already banned
        const isAlreadyBanned = this.banUsers.some(banUser => banUser.email === user.email);

        if (isAlreadyBanned) {
          await Swal.fire({
            title: 'User Already Banned',
            text: 'This user is already banned.',
            icon: 'info',
            confirmButtonText: 'OK',
            customClass: {
              confirmButton: 'myCustomButton'
            },
          });
          return; // Exit the method early if the user is already banned
        }

        // Show confirmation dialog
        const result = await Swal.fire({
          title: 'Are you sure?',
          text: 'Do you really want to ban this user?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#d33',
          cancelButtonColor: '#3085d6',
          confirmButtonText: 'Yes, ban!',
          reverseButtons: true,
          cancelButtonText: 'Cancel'
        });

        if (result.isConfirmed) {
          // Show loading indicator
          Swal.fire({
            title: 'Banning User...',
            text: 'Please wait while the user is being banned.',
            allowOutsideClick: false,
            didOpen: () => {
              Swal.showLoading();
            }
          });

          await axios.post('http://localhost:8083/ban/register', user);

          // Close loading indicator
          Swal.close();

          // Show success message
          Swal.fire(
            'Success!',
            'User has been banned successfully.',
            'success'
          );

          // Fetch users and banned users again
          this.fetchUsers();
          this.fetchBanUsers();
        }
      } catch (error) {
        // Close loading indicator
        Swal.close();
        console.error('Error banning user:', error);

        // Show error message
        Swal.fire(
          'Error!',
          'Failed to ban user. Please try again.',
          'error'
        );
      }
    },


    // async banUser(user) {
    //   console.log("inside Banuser : ", user);
    //   try {
    //     // Show confirmation dialog
    //     const result = await Swal.fire({
    //       title: 'Are you sure?',
    //       text: 'Do you really want to ban this user?',
    //       icon: 'warning',
    //       showCancelButton: true,
    //       confirmButtonColor: '#3085d6',
    //       cancelButtonColor: '#d33',
    //       confirmButtonText: 'Yes, ban!',
    //       cancelButtonText: 'Cancel'
    //     });

    //     if (result.isConfirmed) {
    //       // Show loading indicator
    //       Swal.fire({
    //         title: 'Banning User...',
    //         text: 'Please wait while the user is being banned.',
    //         allowOutsideClick: false,
    //         didOpen: () => {
    //           Swal.showLoading();
    //         }
    //       });

    //       await axios.post('http://localhost:8083/ban/register', user);

    //       // Close loading indicator
    //       Swal.close();

    //       // Show success message
    //       Swal.fire(
    //         'Success!',
    //         'User has been banned successfully.',
    //         'success'
    //       );

    //       // fetch user again
    //       this.fetchUsers();
    //       this.fetchBanUsers();
    //     }
    //   } catch (error) {
    //     // Close loading indicator
    //     Swal.close();
    //     console.error('Error banning user:', error);

    //     // Show error message
    //     Swal.fire(
    //       'Error!',
    //       'Failed to ban user. Please try again.',
    //       'error'
    //     );
    //   }
    // },
    pushy() {
      router.push('/admin')
    },
    ban() {
      router.push('/admin/ban')
    },
    logout() {
      router.push('/admin/login')
    }, pushhh() {
      router.push('/admin/post')
    }
  }
}
</script>


<style lang="scss">
.box2 {
  width: 100%;
  height: auto;

  h2 {
    margin: none;
    padding: none;
    color: #e86f52;
  }

  .v-data-table {
    tr {
      background-color: #e86f52;
      color: #fff;

      .td {
        background-color: #fff !important;
        color: #000 !important;
      }

      .v-btn {
        text-transform: capitalize;
      }
    }

  }
}
</style>