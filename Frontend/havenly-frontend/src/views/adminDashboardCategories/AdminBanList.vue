<template>

  <div class="">
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

      <v-card flat class="w-100">
        <div class="d-flex mb-1 justify-content-end">
          <div class="w-75">
            <h2>Ban user list</h2>
          </div>
          <div class="w-25">
            <v-text-field v-model="search" label="Search by name" prepend-inner-icon="mdi-magnify" variant="outlined"
              hide-details single-line></v-text-field>
          </div>
        </div>
        <v-data-table v-if="!loading" :headers="headers" :items="indexedUsers" :search="search">
          <template v-slot:item="{ item }">
            <tr>
              <td class="td">{{ item.index }}</td>
              <td class="td">{{ item.name }}</td>
              <td class="td">{{ item.email }}</td>
              <td class="td">
                <v-btn @click="deleteUser(item.email)" color="#e86f52" class="me-2">Delete</v-btn>
              </td>
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

            </td>
          </tr>

     
          <tr>
            <td>Ban user one </td>
            <td>banuser@gmail.com</td>
            <td>

              <v-btn class="mt-2 me-3 bg-redbrick text-white">Delete</v-btn>

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
          name: '',
          email: ''
        },
      ],

      loading: false,
      search: '',
      headers: [
        { align: 'start', key: 'index', sortable: false, title: 'No.' },
        { align: 'start', key: 'name', sortable: false, title: 'Name' },
        { key: 'email', title: 'Email', sortable: false, },
        { key: 'action', title: 'Action', sortable: false }
      ],

    }
  },

  computed: {
    indexedUsers() {
      return this.users.map((user, index) => ({
        ...user,
        index: index + 1
      }));
    }
  },

  mounted() {
    localStorage.removeItem('openTab');
    this.fetchUsers();
  },

  methods: {
    async fetchUsers() {
      this.loading = true; // Start loading
      try {
        const response = await axios.get('http://localhost:8083/ban/getAll');
        const data = response.data;
        const mappedData = data.map(user => ({
          name: user.name,
          email: user.email
        }));
        sessionStorage.setItem('users', JSON.stringify(mappedData));
        this.users = mappedData;
      } catch (error) {
        console.error('Error fetching users:', error);
      } finally {
        this.loading = false; // Stop loading
      }
    },

    async deleteUser(email) {
      try {
        // Show confirmation dialog
        const result = await Swal.fire({
          title: 'Are you sure?',
          text: 'You won\'t be able to revert this!',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#d33',
          cancelButtonColor: '#3085d6',
          reverseButtons: true,
          confirmButtonText: 'Yes, delete it!'
        });

        if (result.isConfirmed) {
          // Show loading indicator
          Swal.fire({
            title: 'Deleting...',
            text: 'Please wait while the user is being deleted.',
            allowOutsideClick: false,
            didOpen: () => {
              Swal.showLoading();
            }
          });

          await axios.delete(`http://localhost:8083/ban/delete/${email}`);

          this.users = this.users.filter((u) => u.email !== email);

          // Close the loading indicator
          Swal.close();

          // Show success message
          Swal.fire(
            'Deleted!',
            'User has been deleted.',
            'success'
          );
        }
      } catch (error) {
        // Close the loading indicator in case of error
        Swal.close();
        console.error('Error deleting user:', error);

        // Show error message
        Swal.fire(
          'Error!',
          'Failed to delete user. Please try again.',
          'error'
        );
      }
    },

    // deleteUser(email) {
    //   axios.delete(`http://localhost:8083/ban/delete/${email}`)
    //     .then(() => {

    //       this.users = this.users.filter((u) => u.email !== email);
    //       alert('User deleted successfully.');
    //     })
    //     .catch((error) => {
    //       console.error('Error deleting user:', error);
    //       alert('Failed to delete user. Please try again.');
    //     });

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
