<!-- <template>
  <div style="margin-top: 200px;"></div>
  <div class="container mt-5">
    <div class="row">
      <div class="col-lg-6 col-md-6 col-12 mb-3">
        <div class="row">
          <div class="col-md-7 col-12 mb-3 card-container">


            <div class="ag-format-container">
              <div class="ag-courses_box">

                <div class="ag-courses_item">
                  <a href="#" class="ag-courses-item_link">
                    <div class="ag-courses-item_bg"></div>

                    <div class="bq-container">
                      <blockquote>
                        <span class="ms-5 f-sm">
                          Your website packages are top-notch! They strike the perfect balance
                          between style and functionality, catering to a variety of needs. It's
                          impressive. Great job!
                        </span>
                        <footer>
                          <cite>
                            Daw Hla
                          </cite>
                        </footer>
                      </blockquote>
                    </div>

                    <div class="ag-courses-item_date-box">
                      Date:
                      <span class="ag-courses-item_date">
                        04.11.2022
                      </span>
                    </div>
                  </a>
                </div>
              </div>
            </div>


          </div>
          <div class="col-md-5 col-12 mb-3 card-container d-none d-md-block">
            <div class="card card-two w-100 h-auto p-2">

              <div class="card-two-edit">
                <div class="card-body-1">
                  <div class="d-flex">
                    <v-icon class="mt-1 me-2">mdi-shield-check</v-icon>
                    <h3>Security</h3>
                  </div>


                </div>

                <div class="card-body-2">
                  <h5>hello world</h5>
                </div>

              </div>


            </div>
          </div>
          <div class="col-md-5 col-12 mb-3 card-container d-none d-md-block">
            <div class="card h-auto p-2">

              <div class="card-two-edit">
                <div class="card-body-1 w-100">

                  <div style="text-align: center;">
                    <v-rating :model-value="4.8" color="#e86f52" density="compact" size="medium" half-increments
                      readonly class="mx-auto"></v-rating>

                    <div class="text-grey">
                      4.8 of (100+)
                    </div>
                    <h5>Over all Ranking</h5>
                  </div>

                </div>

                <div class="card-body-2 w-100">

                  <div style="text-align: center;">
                    <v-rating v-model="rate_Value" color="#e86f52" density="compact" size="medium" half-increments
                      class="mx-auto"></v-rating>

                    <div class="d-flex">
                      <div class="color-brick m-auto">
                        {{ rate_Value }}
                      </div>
                      <v-icon v-if="rate_Value > 0" class="edit-btn">mdi-check</v-icon>
                    </div>
                    <h5>Give a Rating!</h5>
                  </div>

                </div>
              </div>


            </div>
          </div>
          <div class="col-md-7 col-12 mb-3 card-container">

            <div class="ag-format-container">
              <div class="ag-courses_box">

                <div class="ag-courses_item">
                  <a href="#" class="ag-courses-item_link">
                    <div class="ag-courses-item_bg-2"></div>


                    <div class="bq-container">
                      <blockquote>
                        <span class="ms-5 f-sm">
                          Your website packages are top-notch! They strike the perfect balance
                          between style and functionality, catering to a variety of needs. It's
                          impressive. Great job!
                        </span>
                        <footer>
                          <cite>
                            Daw Hla
                          </cite>
                        </footer>
                      </blockquote>
                    </div>

                    <div class="ag-courses-item_date-box">
                      Date:
                      <span class="ag-courses-item_date">
                        04.11.2022
                      </span>
                    </div>
                  </a>
                </div>

              </div>
            </div>


          </div>
        </div>
      </div>
      <div class="col-lg-6 col-md-6 col-12">
        <div class="card h-100 p-3 " style="border: none;">
          <div class="card-body d-flex justify-content-center align-items-center">
            <div>
              <h2 class="card-title color-brick">Some valuable feedback from our Subscribers</h2>
              <p class="card-text">


                <span class="ms-5">
                  Unlock the potential of your real estate business with our packages. We offer everything you need to
                  stand out in the real estate world. Become a subscriber and build your home with us. If you subscribe
                  you will get 5 posts as FREE TRIAL. So what are you waiting for. Let't Join with us today.
                </span>

              </p>
              <div class="card-action">
                <v-btn rounded>hello world</v-btn>
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
  data() {
    return {
      cards: Array(4).fill({}),
      rate_Value: 0,
    };
  }
};
</script>

<style scoped lang="scss">
.card-container {
  border: 4px solid red;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: start;
  -ms-flex-align: start;
  align-items: flex-start;

  .ag-format-container {
    padding: 0px;
    width: 100%;
    margin: auto;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
    border-radius: 14px;
  }


  body {
    background-color: #000;
  }

  .ag-courses_box {

    padding: 0 0;
  }

  .ag-courses_item {
    -ms-flex-preferred-size: calc(33.33333% - 30px);
    flex-basis: calc(33.33333% - 30px);

    overflow: hidden;
    border-radius: 14px;
  }

  .ag-courses-item_link {
    display: block;
    padding: 30px 20px;
    background-color: #fff;


    overflow: hidden;
    text-decoration: none;
    position: relative;

    .bq-container {
      z-index: 3;
      transition: none !important;
      color: #000;

      blockquote {
        color: red;
        font-family: Georgia, serif;
        position: relative;
        margin: 0.5em;
        padding: 0.5em 1em 0.5em 2em;

        span {
          color: #000;
        }
      }

      blockquote:before {
        font-family: Georgia, serif;
        position: absolute;
        font-size: 3em;
        line-height: 1;
        top: 0;
        left: 0;
        content: "\201C";
      }

      blockquote:after {
        font-family: Georgia, serif;
        position: absolute;
        float: right;
        font-size: 3em;
        line-height: 1;
        right: 0;
        bottom: -0.5em;
        content: "\201D";
      }

      blockquote footer {
        padding: 0 2em 0 0;
        text-align: right;
      }

      blockquote cite:before {
        content: "\2013";
      }
    }
  }

  .ag-courses-item_link:hover .bq-container {
    blockquote {
      color: #000;
    }
  }

  .ag-courses-item_link:hover .bq .ag-courses-item_link:hover,
  .ag-courses-item_link:hover .ag-courses-item_date {
    text-decoration: none;
    color: #FFF;
  }

  .ag-courses-item_link:hover .ag-courses-item_title {
    color: #fff;
  }

  .ag-courses-item_link:hover .ag-courses-item_bg,
  .ag-courses-item_link:hover .ag-courses-item_bg-2 {
    -webkit-transform: scale(10);
    -ms-transform: scale(10);
    transform: scale(10);
  }

  .ag-courses-item_title {
    min-height: 37px;
    overflow: hidden;
    font-weight: bold;
    font-size: 20px;
    color: #000;
    z-index: 2;
    position: relative;
  }

  .ag-courses-item_date-box {
    font-size: 14px;
    color: #000;

    z-index: 2;
    position: relative;
  }

  .ag-courses-item_date {
    font-weight: bold;
    color: #000;

    -webkit-transition: color .2s ease;
    -o-transition: color .2s ease;
    transition: color .2s ease
  }

  .ag-courses-item_bg,
  .ag-courses-item_bg-2 {
    height: 128px;
    width: 128px;

    z-index: 1;


    border-radius: 50%;

    -webkit-transition: all .5s ease;
    -o-transition: all .5s ease;
    transition: all .5s ease;
  }

  .ag-courses-item_bg {
    position: absolute;
    bottom: -75px;
    right: -75px;
    background-color: #e86f52;

  }

  .ag-courses-item_bg-2 {
    position: absolute;
    top: -75px;
    left: -75px;
    background-color: pink;

  }

  .ag-courses_item:nth-child(2n) .ag-courses-item_bg {
    background-color: #3ecd5e;
  }


  @media only screen and (max-width: 979px) {
    .ag-courses_item {
      -ms-flex-preferred-size: calc(50% - 30px);
      flex-basis: calc(50% - 30px);
    }

    .ag-courses-item_title {
      font-size: 24px;
    }
  }

  @media only screen and (max-width: 767px) {
    .ag-format-container {
      width: 96%;
    }

  }

  @media only screen and (max-width: 639px) {
    .ag-courses_item {
      -ms-flex-preferred-size: 100%;
      flex-basis: 100%;
    }

    .ag-courses-item_title {
      min-height: 72px;
      line-height: 1;

      font-size: 24px;
    }

    .ag-courses-item_link {
      padding: 22px 40px;
    }

    .ag-courses-item_date-box {
      font-size: 16px;
    }
  }
}

.card-one {
  height: auto;
  /* Initial height for Card 1 */
}

.card-two {
  position: absolute;
  bottom: 0;
  left: 0;
}

.card-two-edit {
  overflow: hidden;
  width: 100%;
  height: auto;
  border: 4px dotted green;
  position: relative;

  .card-body-2 {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    bottom: -200px;

    .edit-btn {
      border-radius: 50%;
      padding: 4px;
      color: #fff;
      margin-top: -1px;
      margin-left: -4px;
      background-color: #e86f52;
      cursor: pointer;
    }
  }

  &:hover {
    .card-body-1 {

      transform: translateY(-100%);
    }

    .card-body-2 {
      position: absolute;
      bottom: 0px;
    }
  }
}


.card-four {
  height: 300px;
  /* Initial height for Card 4 */
}

@media (max-width: 1000px) {
  .card-four {
    height: 100%;
    /* Initial height for Card 4 */
  }

  .container {
    display: flex;
    flex-direction: column;
  }

  .col-md-7 {
    order: 1;
    width: 100% !important;
  }

  .col-md-5 {
    display: none !important;
  }

  .col-md-7:nth-child(4) {
    order: 2;
    width: 100% !important;
  }

  .col-lg-6.col-md-6.col-12 {
    order: 3;
  }

  .card-container {
    position: relative;
  }

  .card-container .card {
    height: auto;
  }


}
</style> -->


<!-- <template>
  <div style="margin-top: 200px;"></div>
  <div class="container">

    <div class="map-main-container">

    </div>
    <div class="row">


      <div class="col-12 col-md-6 col-lg-3">
        <div class="card">
          <div class="card-body">
            1
          </div>
        </div>
      </div>

      <div class="col-12 col-md-6 col-lg-3">
        <div class="card">
          <div class="card-body">
            2 </div>
        </div>
      </div>

      <div class="col-12 col-md-6 col-lg-3">
        <div class="card">
          <div class="card-body">
            3 </div>
        </div>
      </div>

      <div class="col-12 col-md-6 col-lg-3">
        <div class="card">
          <div class="card-body">
            4 </div>
        </div>
      </div>


    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cards: [
        { id: 1, title: 'Card 1' },
        { id: 2, title: 'Card 2' },
        { id: 3, title: 'Card 3' },
        { id: 4, title: 'Card 4' },
      ]
    }
  }
}
</script>

<style>
.card {
  margin: 10px 0;

}
</style> -->

<!-- 
<template>

  <div class="first-index-apk5 mt-5">
    <v-container>
      <div class="row">

        <div class="col-12 map-container-edit p-0 m-0">

          <div class="row ">
            <div class="col-6  p-0 m-0">
              <div class="map-header-text me-auto">
                <h4 class=" fw-bold">Search post by location</h4>
              </div>
            </div>

            <div class="col-6  p-0 m-0">
              <div class="map-header-text-2 ms-auto d-flex">
                <button class="btn btn-danger btn-edit me-2" data-bs-toggle="modal"
                  data-bs-target="#editModal">Search</button>
                <button class="btn btn-danger btn-edit">Clear
                </button>
              </div>
            </div>
          </div>

          <div class="row " style="backdrop-filter: blur(10px);">


            <div class="col-12 col-md-6 col-lg-3 p-0 m-0">
              <div class="card">
                <div class="card-body p-0 m-0">

                  <select class="form-select form-select-lg " aria-label="Large select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                  </select>

                </div>
              </div>
            </div>

            <div class="col-12 col-md-6 col-lg-3 p-0 m-0">
              <div class="card">
                <div class="card-body p-0 m-0">

                  <select class="form-select form-select-lg " aria-label="Large select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                  </select>

                </div>
              </div>
            </div>

            <div class="col-12 col-md-6 col-lg-3 p-0 m-0">
              <div class="card">
                <div class="card-body p-0 m-0">

                  <select class="form-select form-select-lg " aria-label="Large select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                  </select>

                </div>
              </div>
            </div>

            <div class="col-12 col-md-6 col-lg-3 p-0 m-0">
              <div class="card">
                <div class="card-body p-0 m-0">

                  <select class="form-select form-select-lg" aria-label="Large select example">
                    <option selected>Open this select menu</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                  </select>

                </div>
              </div>
            </div>


          </div>

          <div class="row">
            <div class="col-12 map-data-edit p-0 m-0">
              <GoogleMap :key="mapLocations.length" api-key="AIzaSyBqvZfzDW7YlZHtfaR-5l1v8f0YkMzswQM" :center="center"
                :zoom="zoom" class="map-form w-100">
                <Marker v-for="(location, index) in mapLocations" :key="index"
                  :options="{ position: { lat: parseFloat(location.latitude), lng: parseFloat(location.longitude) } }" />
              </GoogleMap>
            </div>
          </div>

        </div>

      </div>
    </v-container>

  </div>

</template>
<script>
export default {
  name: 'helloW'
}
</script> -->
<!-- <style lang="scss" scoped>
.first-index-apk5 {
  width: 100%;
  height: auto;
  background-image: url("@/assets/img/main-bg.jpg");

  position: relative;

  padding: 60px 0px;
  background-repeat: no-repeat;
  background-size: cover;

  .map-container-edit {
    padding: 0;
    width: 100%;
    height: 100%;

    .map-header-text {
      border: 6px solid #e86f52;
      border-bottom: 0;
      width: 320px;
      text-align: center;
      padding-top: 16px;
      padding-bottom: 16px;
      border-radius: 20px 20px 0 0;
      backdrop-filter: blur(10px);
      -webkit-backdrop-filter: blur(10px);
      background: rgba(255, 255, 255, 0.5);
    }

    .map-header-text-2 {
      width: 320px;
      text-align: center;
      padding-top: 16px;
      padding-bottom: 16px;
      border-radius: 20px 20px 0 0;

      .btn-edit {
        width: 50%;
      }
    }

    .map-data-edit {
      width: 100%;
      height: 300px;
      z-index: 1;
      padding: 0;
      margin: 0;
      background-color: aqua;
      display: flex;

      align-items: center;
      justify-content: center;

      .map-form {
        z-index: 30;
        display: flex;
        flex-direction: column;
        width: 100%;
        height: 100%;
      }
    }
  }
}

.first-index-apk5::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}

@media only screen and (max-width: 991px) {
  .first-index-apk5 {
    padding: 20px;

    .map-container-edit {
      .map-data-edit {
        height: 370px;
      }

      .map-search-btn {
        padding: 18px 28px;
      }

      .map-header-text {
        border-color: yellow;
        padding-top: 16px;
        padding-bottom: 24px;
        width: 320px;
      }

      .map-header-text-2 {
        width: 270px;
        display: flex;
        justify-content: space-around;
        border: 1px solid red;

        .btn-edit {
          width: 35%;
        }
      }
    }
  }
}

@media only screen and (max-width: 620px) {
  .first-index-apk5 {
    .map-container-edit {

      .map-header-text {
        border-color: yellow;
        padding-top: 16px;
        padding-bottom: 24px;
        width: 300px;
      }

      .map-header-text-2 {
        width: 220px;
        display: flex;
        justify-content: space-around;
        border: 1px solid red;

        .btn-edit {
          width: 35%;
        }
      }
    }
  }
}

@media only screen and (max-width: 555px) {
  .first-index-apk5 {
    .map-container-edit {
      .map-header-text {
        border-color: yellow;
        padding-top: 16px;
        padding-bottom: 24px;
        width: 250px;
        font-size: 12px !important;
      }

      .map-header-text-2 {
        width: 190px;
        display: flex;
        justify-content: space-around;
        border: 1px solid red;

        .btn-edit {
          width: 35%;
        }
      }
    }
  }
}
</style> -->

<template>
  <div style="margin-top: 200px;"></div>
  <div class="profile-main-card">
    <div class="container">
      <div class="left-card">
        <div class="profile-card main-profile">
          <div class="profile-box">
            <div class="profile-box-data pt-2 pb-5">
              <div class="profile-img-container">
                <v-img :src="profileImage || profilePicture" class="profile-img" alt="Profile Picture" padding=5px
                  max-height="150" max-width="150" contain />
              </div>
              <div class="mt-3 p-3 mx-auto">
                <div v-if="user_data !== null">
                  <v-text-field density="comfortable" clear-icon="mdi-close-circle" rounded="lg" variant="plain"
                    v-model="items.name" label="Name" placeholder="User Name" readonly></v-text-field>
                  <v-text-field density="comfortable" clear-icon="mdi-close-circle" rounded="lg" variant="plain"
                    v-model="items.email" label="Email" placeholder="Email" readonly></v-text-field>
                  <v-text-field density="comfortable" clear-icon="mdi-close-circle" rounded="lg" variant="plain"
                    v-model="items.phone" label="Phone" placeholder="Contact No" readonly></v-text-field>
                  <v-text-field density="comfortable" clear-icon="mdi-close-circle" rounded="lg" variant="plain"
                    v-model="items.nrc" label="NRC" placeholder="NRC" readonly></v-text-field>
                </div>
              </div>
              <div class="d-flex justify-center mt-2">
                <p style="width: 90px; height:40px" class="mr-2 me-3">
                  <span class="custom-button d-flex align-center" @click="openEditDialog"
                    style="width: 100%; justify-content: center;">
                    <v-icon>mdi-pencil</v-icon>&nbsp;Edit
                  </span>
                </p>
                <v-dialog v-model="editDialog" class="create-pop-up" persistent>
                  <form @submit.prevent="update" class="form-edit2">
                    <v-row cols="12" class="mx-auto mb-3">
                      <h3>Edit Profile</h3>
                    </v-row>
                    <button type="button" class="close-btn" @click="closeEditDialog">
                      <v-icon>mdi-close-circle</v-icon>
                    </button>
                    <div class="profile-pic">
                      <div class="profile-img-container1 align-center" @click="triggerFileInput">
                        <v-img :src="profileImage || profilePicture" class="profile-img1 mb-2" alt="Profile Picture"
                          max-height="150" max-width="150" contain />
                        <v-icon class="edit-icon">mdi-pencil</v-icon>
                        <input type="file" accept="image/png, image/jpeg, image/bmp" ref="fileInput"
                          style="display: none;" prepend-icon="mdi-camera" @change="handleFileUpload" />
                      </div>
                    </div>
                    <div style="margin-top: 150px;">
                      <v-text-field density="comfortable" rounded="lg" variant="solo" v-model="user.name"
                        :error-messages="nameErrorMessages" label="Name" placeholder="Enter your name"></v-text-field>
                      <v-text-field density="comfortable" rounded="lg" variant="solo" v-model="user.phone"
                        :error-messages="phoneErrorMessages" label="Phone Number"
                        placeholder="Enter your phone number"></v-text-field>
                    </div>
                    <p>
                      <v-row cols="12" class="w-100 mt-4">
                        <v-btn class="submit ms-auto me-3" type="update"
                          style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                          Update
                        </v-btn>
                      </v-row>
                    </p>
                  </form>
                </v-dialog>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="right-cards">
        <div class="profile-card">
          <div class="profile-box">
            <div class="profile-box-data">
              <div class="p-3">
                <div class="d-flex w-100 p-0 justify-space-between">
                  <h5>Account info status</h5>
                  <p style="color: #fff; padding: 4px 12px; background-color: #4CAF50; border-radius: 17px;">
                    Editable<i class="fa-solid fa-check ms-1"></i>
                  </p>
                </div>
                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                <div class="d-flex justify-space-between">
                  <p class="fw-bold">Purchased package</p>
                  <p
                    style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                    <span @click="packageDialogOpen" class="d-flex align-center">
                      <v-icon>mdi-store</v-icon>&nbsp;Check
                    </span>
                  </p>
                </div>
                <div class="d-flex justify-space-between">
                  <p class="fw-bold">Reset password</p>
                  <p
                    style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                    <span class="d-flex align-center" @click="openDialog">
                      <v-icon>mdi-lock</v-icon>&nbsp;Reset
                    </span>
                  </p>
                  <v-dialog v-model="packageDialog" class="create-pop-up">
                    <div class="pop-up-subscribe">
                      <div class="d-flex justify-space-between">
                        <p>Subscribed Package</p>
                      </div>
                      <button class="close-btn1" @click="packageDialogClose">
                        <v-icon>mdi-close-circle</v-icon>
                      </button>
                    </div>
                  </v-dialog>
                  <v-dialog v-model="resetdialog" class="create-pop-up" persistent>
                    <form @submit.prevent="submit" class="form-edit2">
                      <v-row cols="12" class="mx-auto mb-3">
                        <h3>Change Password</h3>
                      </v-row>
                      <button class="close-btn" @click="closeDialog">
                        <v-icon>mdi-close-circle</v-icon>
                      </button>
                      <div v-if="user_data !== null">
                        <v-text-field density="comfortable" clear-icon="mdi-close-circle" clearable rounded="lg"
                          variant="solo" v-model="items.email" :rules="[validateGmail]" label="G-mail"></v-text-field>
                        <v-text-field density="comfortable" rounded="lg" variant="solo" v-model="change_pw.password"
                          :append-icon="visible ? 'mdi-eye' : 'mdi-eye-off'" :rules="[validateResetPassword]"
                          :type="visible ? 'text' : 'password'" class="input-group--focused"
                          hint="At least 8 characters" label="Password" name="input-10-2"
                          @click:append="visible = !visible"></v-text-field>
                        <v-text-field density="comfortable" rounded="lg" variant="solo" v-model="change_pw.new_password"
                          :append-icon="visible1 ? 'mdi-eye' : 'mdi-eye-off'" :rules="[validateResetConfirmPassword]"
                          :type="visible1 ? 'text' : 'password'" class="input-group--focused"
                          hint="At least 8 characters" label="New Password" name="input-10-2"
                          @click:append="visible1 = !visible1"></v-text-field>
                      </div>
                      <p>
                        <v-row cols="12" class="w-100 mt-4">
                          <v-btn @click="resetPassword" class="submit ms-auto me-3" type="submit"
                            style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                            Reset
                          </v-btn>
                        </v-row>
                      </p>
                    </form>
                  </v-dialog>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="profile-card">
          <div class="profile-box">
            <div class="profile-box-data">
              <div class="p-3">
                <div class="d-flex w-100 p-0 justify-space-between">
                  <h5>Account progress status</h5>
                </div>
                <v-divider class="mt-0 p-0 mb-2" :thickness="3"></v-divider>
                <div class="d-flex justify-space-between">
                  <p class="fw-bold">Deleted pending Posts</p>
                  <p
                    style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                    <span @click="pendingDialogOpen" class="d-flex align-center">
                      <v-icon>mdi-trash-can</v-icon>&nbsp;Check
                    </span>
                  </p>
                  <v-dialog v-model="pendingDialog" class="create-pop-up">
                    <div class="pop-up-subscribe">
                      <div class="d-flex justify-space-between">
                        <p>Deleted pending posts</p>
                      </div>
                      <button class="close-btn1" @click="pendingDialogClose">
                        <v-icon>mdi-close-circle"></v-icon>
                      </button>
                    </div>
                  </v-dialog>
                </div>
                <div class="d-flex justify-space-between">
                  <p class="fw-bold">Chat history</p>
                  <p
                    style="color: #fff; padding: 4px 14px; background-color: #E97559; border-radius: 17px; cursor: pointer;">
                    <span class="d-flex align-center" @click="historyDialogOpen">
                      <v-icon>mdi-chat</v-icon>&nbsp;Check
                    </span>
                  </p>
                  <v-dialog v-model="historyDialog" class="create-pop-up">
                    <div class="pop-up-subscribe">
                      <div class="d-flex justify-space-between">
                        <p>Chat history</p>
                      </div>
                      <button class="close-btn1" @click="historyDialogClose">
                        <v-icon>mdi-close-circle"></v-icon>
                      </button>
                    </div>
                  </v-dialog>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
