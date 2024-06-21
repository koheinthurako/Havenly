<template>

    <div class="post-detail-view-page no-select mb-lg-5">

        <v-overlay :model-value="overlay" class="align-center justify-center">
            <v-progress-circular color="#e86f52" size="72" width="8" indeterminate></v-progress-circular>
        </v-overlay>

        <div class="overlay-for-mobile" v-if="showCard3"></div>

        <!-- bootstrap container is getting errro ;( -->
        <v-container>

            <div class="row ">

                <div class="col-md-12 ">
                    <div class="left">

                        <div class="col-md-12">
                            <div class="header me-3">



                                <div class="row">

                                    <!-- <div class="col-10 col-md-8 col-sm-10"></div> -->

                                    <div
                                        :class="getData === 'Admin_View' ? 'col-12 col-md-8 col-sm-12' : (getData === 'Success' ? 'col-10 col-md-8 col-sm-10' : '')">
                                        <h3 class="color-brick">{{ post.title }}</h3>
                                        <div class="d-flex"><v-icon class="s-icon">mdi-map-marker</v-icon>
                                            <p>{{ post.province }} / {{
                                                post.region }} / {{
                                                    post.country }}</p>
                                        </div>
                                    </div>

                                    <!-- for mobile view search icon start -->
                                    <div class="col-2 d-block d-sm-none" v-if="getData === 'Success'">
                                        <v-card v-click-outside="onClickOutside" :elevation="showCard3 ? 20 : 0"
                                            class="ms-auto search-card d-block d-sm-none"
                                            :class="[{ 'search-card-transform': showCard3 }]">

                                            <v-card-actions class="m-0 p-0 h-0" min-height="0">
                                                <v-btn icon @click="searchToggle" class="sideCorner-icon"
                                                    :class="[{ 'sideCorner-icon-searchForm': showCard3 }]">
                                                    <h5 :hidden="showCard3">
                                                        <v-icon class="mt-2">mdi-magnify</v-icon>
                                                    </h5>
                                                    <h5 :hidden="!showCard3" class="m-auto" style="color: #000;">
                                                        <v-icon class="mx-auto">mdi-close</v-icon>
                                                    </h5>
                                                </v-btn>
                                            </v-card-actions>

                                            <v-expand-transition>
                                                <div v-show="showCard3">

                                                    <v-card-text class="m-0 p-2">
                                                        <div v-show="showCard3" class="mb-4 text-header">
                                                            <h5>Search posts</h5>
                                                        </div>

                                                        <v-row v-show="showCard3">
                                                            <v-col cols="12">
                                                                <v-menu v-model="menuSearch"
                                                                    :close-on-content-click="false" offset-y
                                                                    transition="scale-transition" max-height="auto">
                                                                    <template v-slot:activator="{ on, attrs }">
                                                                        <v-text-field hide-details
                                                                            class="edit-search-bar" variant="outlined"
                                                                            ref="activator" v-model="search"
                                                                            label="Search posts by name"
                                                                            prepend-inner-icon="mdi-magnify" clearable
                                                                            v-bind="attrs" v-on="on || {}"
                                                                            @input="onSearchMobile"></v-text-field>

                                                                        <v-list v-if="filteredTitles.length"
                                                                            class="p-0 filter-list-edit">
                                                                            <h4 class="ms-3 mt-2"
                                                                                style="color: #e86f52;">
                                                                                Available
                                                                                posts</h4>
                                                                            <v-list-item v-for="post in filteredTitles"
                                                                                :key="post.id"
                                                                                @click="handleItemClick(post)"
                                                                                style="border-bottom: 1px solid #000;">
                                                                                <v-list-item-title>
                                                                                    <v-chip v-if="post.type === 'Sell'"
                                                                                        prepend-icon="mdi-checkbox-marked-circle"
                                                                                        size="small" rounded-pill
                                                                                        color="red" variant="flat"
                                                                                        class="me-1">
                                                                                        {{ post.type }}
                                                                                    </v-chip>
                                                                                    <v-chip v-else
                                                                                        prepend-icon="mdi-checkbox-marked-circle"
                                                                                        size="small" rounded-pill
                                                                                        color="green" variant="flat"
                                                                                        class="me-1">
                                                                                        {{ post.type }}
                                                                                    </v-chip>
                                                                                    {{ post.title }}
                                                                                </v-list-item-title>
                                                                            </v-list-item>
                                                                        </v-list>

                                                                        <v-alert v-else-if="search" type="warning"
                                                                            class="ma-0 mt-2">
                                                                            No post available
                                                                        </v-alert>

                                                                    </template>

                                                                </v-menu>

                                                            </v-col>
                                                        </v-row>

                                                    </v-card-text>

                                                </div>
                                            </v-expand-transition>
                                        </v-card>
                                    </div>
                                    <!-- for mobile view search icon end -->

                                    <!-- search bar start -->
                                    <div class="col-4 col-md-4 col-sm-4 d-none d-sm-block" v-if="getData === 'Success'">
                                        <div class="search-bar mt-2 ">
                                            <v-row>
                                                <v-col cols="12">
                                                    <v-menu v-model="menu" :close-on-content-click="false" offset-y
                                                        :activator="activator" transition="scale-transition"
                                                        max-height="200">
                                                        <template v-slot:activator="{ on, attrs }">
                                                            <v-text-field class="edit-search-bar" variant="outlined"
                                                                ref="activator" v-model="search"
                                                                label="Search posts by name"
                                                                prepend-inner-icon="mdi-magnify" clearable
                                                                v-bind="attrs" v-on="on || {}"
                                                                @input="onSearch"></v-text-field>
                                                        </template>
                                                        <v-list v-if="filteredTitles.length" class="p-0">
                                                            <h4 class="ms-3 mt-2" style="color: #e86f52;">
                                                                Available
                                                                posts</h4>
                                                            <v-list-item v-for="post in filteredTitles" :key="post.id"
                                                                @click="handleItemClick(post)"
                                                                style="border-bottom: 1px solid #000;">
                                                                <v-list-item-title>
                                                                    <v-chip v-if="post.type === 'Sell'"
                                                                        prepend-icon="mdi-checkbox-marked-circle"
                                                                        size="small" rounded-pill color="red"
                                                                        variant="flat" class="me-1">
                                                                        {{ post.type }}
                                                                    </v-chip>
                                                                    <v-chip v-else
                                                                        prepend-icon="mdi-checkbox-marked-circle"
                                                                        size="small" rounded-pill color="green"
                                                                        variant="flat" class="me-1">
                                                                        {{ post.type }}
                                                                    </v-chip>
                                                                    {{ post.title }}
                                                                </v-list-item-title>
                                                            </v-list-item>
                                                        </v-list>

                                                        <v-alert v-else-if="search" type="warning" class="ma-0">
                                                            No post available
                                                        </v-alert>
                                                    </v-menu>
                                                </v-col>
                                            </v-row>
                                        </div>
                                    </div>
                                    <!-- search bar end -->


                                    <!-- for admin start -->
                                    <div :class="getData === 'Admin_View' ? 'col-12 col-md-4 col-sm-12' : ''"
                                        v-if="getData === 'Admin_View'">
                                        <v-card :elevation="10" class="p-2">
                                            <div v-if="post.status === 'pending'">
                                                <div class="d-flex justify-space-between align-items-center mb-2">
                                                    <h4 class="p-0 m-0" style="color:#e86f52;">Post status </h4>
                                                    <v-chip rounded class="bg-warning"><v-icon
                                                            class="me-1">mdi-clock-outline</v-icon>{{
                                                                post.status }}
                                                    </v-chip>
                                                </div>
                                                <hr class="mx-auto">
                                                <div class="admin-action-btn d-flex justify-space-between">
                                                    <v-btn color="green" elevation="0" variant="outlined"
                                                        @click="approve(post)"
                                                        style="text-transform:capitalize;">Approve
                                                        post</v-btn>
                                                    <v-spacer></v-spacer>
                                                    <v-btn color="red" elevation="0" variant="outlined"
                                                        @click="cancel(post)" style="text-transform:capitalize;">Decline
                                                        post</v-btn>
                                                </div>
                                            </div>
                                            <div v-else-if="post.status === 'complete'">
                                                <v-alert text="You approved this post." type="success"></v-alert>
                                                <!-- <h4 class="mt-3" style="color:#e86f52;"></h4> -->
                                            </div>
                                        </v-card>
                                    </div>
                                    <!-- for admin end -->
                                </div>



                            </div>
                        </div>

                        <hr class="mx-auto">



                        <div class="row images p-0 m-0">
                            <div class="col-md-8 col-sm-12 mb-2 p-0 m-0">
                                <div class="img-1">
                                    <v-img :src="post.photo_url[0]"></v-img>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-12 p-0 m-0">
                                <div class="row p-0 m-0">
                                    <div class="col-6 col-md-12 p-0 ">
                                        <div class="img-2">
                                            <v-img :src="post.photo_url[1]"></v-img>
                                        </div>
                                    </div>

                                    <div class="col-6 col-md-12 p-0  h-auto " @click="showMoreImages = !showMoreImages"
                                        style="cursor:pointer;">

                                        <div class="img-2">
                                            <div class="img-overlay">
                                                <div class="see-first">
                                                    <div class="d-flex">
                                                        <v-icon>mdi-image-multiple</v-icon>
                                                        <h5>More images</h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <v-img
                                                :src="post.photo_url[2] != null ? post.photo_url[2] : post.photo_url[1]" />
                                        </div>

                                    </div>
                                </div>
                            </div>

                        </div>



                        <hr class="mx-auto">


                        <div class="row">


                            <div :class="['col-md-8', { 'col-md-12': getData === 'Success' }]">

                                <!-- start here -->
                                <div class="row">
                                    <div class="description">
                                        <h4 class="color-brick mb-4">Post Description</h4>

                                        <p class="detail-text" v-html="formatDesc(post.description)"></p>
                                    </div>
                                </div>



                                <hr class="mx-auto mb-lg-4">


                                <div class="row">
                                    <!-- <h5 class="color-brick mb-4">ပို့စ် အသေးစိတ်အချက်များ</h5> -->

                                    <div class="row p-0">
                                        <div class="col-6 ">
                                            <div class="d-flex"><v-icon class="s-icon">mdi-map-marker</v-icon>
                                                <p> &nbsp; {{ post.province }} / {{
                                                    post.region }} / {{
                                                        post.country }}</p>
                                            </div>
                                            <div class="d-flex"><v-icon class="s-icon">mdi-office-building</v-icon>
                                                <p> &nbsp; {{ post.property_type }}</p>
                                            </div>

                                            <div class="d-flex"><v-icon class="s-icon">mdi-arrow-expand-all</v-icon>
                                                <p> &nbsp; {{ post.area }} Square Ft </p>
                                            </div>

                                            <div v-if="post.deposit" class="d-flex">
                                                <v-icon class="s-icon">mdi-cash</v-icon>
                                                <p> &nbsp; {{ post.deposit }} (Deposit)</p>
                                            </div>

                                            <div v-if="post.least_contract" class="d-flex">
                                                <v-icon class="s-icon">mdi-file</v-icon>
                                                <p> &nbsp; {{ post.least_contract }} (Contract)</p>
                                            </div>

                                        </div>

                                        <div class="col-6">

                                            <div class="d-flex"><v-icon class="s-icon">mdi-tag-multiple</v-icon>
                                                <p> &nbsp; Price</p><br>

                                            </div>
                                            <p class="price"> {{ post.price }}</p>

                                        </div>
                                    </div>
                                </div>


                                <hr class="mx-auto mb-lg-4">



                                <v-dialog v-model="reqDialog" width="450" transition="dialog-top-transition">

                                    <v-card class="request-detail-dialog">
                                        <div class="p-2">
                                            <h5 class="text-center">Make enquiry</h5>
                                            <hr class="mx-auto">
                                            <div class="d-flex">

                                                <v-btn class="half-btn w-50" style="text-transform:capitalize;">{{
                                                    post.property_type
                                                }}</v-btn>
                                                <v-btn class="half-btn w-50 bg-green"
                                                    style="text-transform:capitalize;">
                                                    <!-- {{this.getPostType(post.post_id) }} -->
                                                    {{ post.post_type }}
                                                </v-btn>

                                            </div>
                                            <hr class="mx-auto">
                                            <v-text-field focused density="compact" rounded="lg" variant="outlined"
                                                v-model="getUser.name" label="Name *" required class="m-0"
                                                readonly></v-text-field>
                                            <v-text-field focused density="compact" rounded="lg" variant="outlined"
                                                v-model="getUser.email" label="Gmail *" required
                                                readonly></v-text-field>
                                            <v-text-field readonly density="compact" focused v-model="getUser.phone"
                                                :prefix="phonePrefix" variant="outlined" label="Phone Number">
                                                <template v-slot:prepend-inner>
                                                    <img v-if="post.country !== 'Thailand'" :src="selectedCountry.flag"
                                                        alt="flag" class="me-2" style="height: 24px;" />
                                                    <img v-if="post.country === 'Thailand'" :src="selectedCountry.flag2"
                                                        alt="flag" class="me-2" style="height: 24px;" />
                                                </template>
                                            </v-text-field>

                                            <v-textarea v-model="getDescription" variant="outlined" label="Label"
                                                auto-grow="false"></v-textarea>
                                            <v-btn @click="interest" class="request-btn w-100 "
                                                style="text-transform:capitalize; background-color:#e86f52;">
                                                <v-icon>mdi-bookmark-outline</v-icon>
                                                Request Details
                                            </v-btn>
                                        </div>
                                    </v-card>

                                </v-dialog>

                                <!-- image edit start -->
                                <div class="row more-image-container" v-show="showMoreImages">
                                    <div class=" col-md-10 col-sm-12 p-0 m-0">
                                        <!-- first show loading to wait image load -->

                                        <div class="edit-size mx-auto no-select">


                                            <div class="loading-card" v-show="overlayInEditLoading">
                                                <v-progress-circular color="#e86f52" size="72" width="8"
                                                    indeterminate></v-progress-circular>
                                            </div>

                                            <div class="d-flex justify-space-between align-items-center">
                                                <h3 class="text-start edit-t p-0 mt-2 ms-2 mb-0">More images</h3>
                                                <v-btn icon class="me-2 mb-0 custom-close"
                                                    @click="handleShowMoreImages">
                                                    <v-icon>mdi-close</v-icon>
                                                </v-btn>
                                            </div>
                                            <swiper :style="{
                                                '--swiper-navigation-color': '#fff',
                                                '--swiper-pagination-color': '#fff',
                                            }" :loop="post.photo_url.length > 1" :autoplay="{
                                                delay: 2500,
                                                disableOnInteraction: false,
                                            }" :keyboard="{
                                                enabled: true,
                                            }" :mousewheel="true" :spaceBetween="10" :navigation="true"
                                                :thumbs="{ swiper: thumbsSwiper }" :modules="modules" class="mySwiper2"
                                                ref="mainSwiper">
                                                <swiper-slide v-for="(photo, index) in post.photo_url" :key="index">
                                                    <v-img :src="photo" class="upper-image card-img"
                                                        style="cursor: pointer;"></v-img>
                                                </swiper-slide>
                                            </swiper>
                                            <hr class="mx-auto my-1 py-1">
                                            <swiper @swiper="setThumbsSwiper" :loop="post.photo_url.length > 1"
                                                :spaceBetween="1" :slidesPerView="4" :freeMode="false"
                                                :watchSlidesProgress="true" :modules="modules" class="mySwiper"
                                                ref="thumbsSwiper">
                                                <swiper-slide v-for="(photo, index) in post.photo_url" :key="index">
                                                    <v-img :src="photo" class="under-image card-img"
                                                        :class="{ 'is-active': activeIndex === index }"
                                                        style="cursor: pointer;"></v-img>
                                                </swiper-slide>
                                            </swiper>

                                        </div>

                                    </div>
                                </div>
                                <!-- image edit end -->


                                <!-- <v-bottom-sheet v-model="sheet" inset class="v-btn-sheet">

                                    <v-card class="text-center v-btn-sheet-card">
                                        <v-card-text class="p-0 m-0">
                                            <div class="d-flex justify-space-between py-2 px-4">
                                                <h4 style="color:#e86f52;">More images</h4>
                                                <v-btn icon color="#e86f52" variant="elevated" @click="sheet = !sheet">
                                                    <v-icon>mdi-close-circle-outline</v-icon>
                                                </v-btn>
                                            </div>
                                            <br>

                                            <v-card style="background-color:transparent; box-shadow: none;">
                                                <v-card-text>

                                                    <v-carousel hide-delimiters class="testing-edit">
                                                        <template v-slot:prev="{ props }">
                                                            <v-btn icon color="#e86f52" variant="elevated"
                                                                @click="props.onClick">
                                                                <v-icon>mdi-chevron-left</v-icon>
                                                            </v-btn>
                                                        </template>
                                                        <template v-slot:next="{ props }">
                                                            <v-btn icon color="#e86f52" variant="elevated"
                                                                @click="props.onClick"><v-icon>mdi-chevron-right</v-icon></v-btn>
                                                        </template>
                                                        <v-carousel-item v-for="(image, index) in post.photo_url"
                                                            :key="index" :src="image">
                                                        </v-carousel-item>
                                                    </v-carousel>

                                                </v-card-text>
                                            </v-card>
                                        </v-card-text>
                                    </v-card>
                                </v-bottom-sheet> -->


                                <v-btn v-if="getData === 'Success'" class="req-btn" @click="openDialog">
                                    Make interest
                                </v-btn>

                                <!-- end here -->

                            </div>
                            <div v-if="getData === 'details'" class="col-md-4">
                                <v-card class="interst-posts mb-3">
                                    <h5 class="header">
                                        <!-- <v-icon>mdi-star</v-icon> -->
                                        interested users
                                    </h5>
                                    <br><br>

                                    <div class="p-2 mt-2">

                                        <template v-if="interestedUser.length > 0">
                                            <div class="post-icon mb-3 row" v-for="data in interestedUser"
                                                :key="data.postId">
                                                <div class="pi-overlay"></div>
                                                <div class="col-3 p-0">
                                                    <v-img :src="avatar" class="w-100 h-100" cover></v-img>
                                                </div>
                                                <div class="col-9">
                                                    <p class="p-0 m-0 mt-1">
                                                        <span>{{ data.name }}</span> maked interest <span>{{
                                                            data.daysAgo }}</span>.
                                                    </p>
                                                </div>
                                            </div>
                                        </template>
                                        <template v-else>
                                            <p>No user interested</p>
                                        </template>

                                    </div>
                                </v-card>
                            </div>
                            <div v-else-if="getData === 'Admin_View'" class="col-md-4">
                                <v-card class="interst-posts mb-3">
                                    <h5 class="header">
                                        <!-- <v-icon>mdi-star</v-icon> -->
                                        Uploaded user info
                                    </h5>
                                    <br><br>

                                    <div class="p-2 mt-2">

                                        <!-- uploaded user card start -->
                                        <div
                                            class="uploaded-user-container d-flex justify-space-between align-items-center">

                                            <!-- left card start -->
                                            <v-card :elevation="showCard ? 20 : 0" class="me-auto uploaded-user-card"
                                                :class="[{ 'uploaded-user-card-transform': showCard, 'uploaded-user-card-transform2': showCard2 }]">

                                                <v-card-actions class="m-0 py-0 uploaded-card-action">

                                                    <v-avatar class="ava-img-base" :class="[{ 'ava-img': showCard }]">
                                                        <v-img alt="John" :src="img"></v-img>
                                                    </v-avatar>
                                                    <p class="mt-2 ms-3 fw-bold ava-data-base"
                                                        :class="[{ 'ava-data': showCard }]">
                                                        {{ postOwner.name }}</p>
                                                    <v-spacer></v-spacer>

                                                    <v-btn icon class="action-close" @click="showCard = !showCard">
                                                        <v-icon
                                                            :icon="showCard ? 'mdi-close' : 'mdi-chevron-down'"></v-icon>
                                                    </v-btn>
                                                </v-card-actions>

                                                <v-expand-transition>
                                                    <div v-show="showCard">
                                                        <v-card-text class="text-center mt-1">
                                                            <h3 class="m-0 p-0 color-brick">{{ postOwner.name }}
                                                            </h3>

                                                            <p class="m-0 mt-2 p-0">{{ postOwner.email }}</p>
                                                            <p class="m-0 mt-2 p-0">{{ postOwner.phone }}</p>
                                                            <div class="action-group-p mt-2">

                                                                <!-- menu btn 1 start -->
                                                                <v-menu min-width="200px" rounded>
                                                                    <template v-slot:activator="{ props }">
                                                                        <v-btn rounded v-bind="props">
                                                                            <v-badge color="red"
                                                                                :content="sellPosts.length">
                                                                            </v-badge>
                                                                            Sell
                                                                        </v-btn>
                                                                    </template>
                                                                </v-menu>
                                                                <!-- menu btn 1 end -->

                                                                <!-- menu btn 2 start -->
                                                                <v-menu min-width="200px" rounded>
                                                                    <template v-slot:activator="{ props }">
                                                                        <v-btn rounded v-bind="props" class="mx-3">
                                                                            <v-badge color="red"
                                                                                :content="rentPosts.length">
                                                                            </v-badge>
                                                                            Rent
                                                                        </v-btn>
                                                                    </template>
                                                                </v-menu>
                                                            </div>
                                                        </v-card-text>
                                                    </div>
                                                </v-expand-transition>
                                            </v-card>
                                            <!-- left card end -->

                                            <!-- right card start  -->
                                            <!-- <v-btn :icon="!showCard2" @click="showCard2 = !showCard2"
                                                class="sideCorner-icon"
                                                :class="[{ 'sideCorner-icon-transform': showCard, 'sideCorner-icon-transform2': showCard2 }]">
                                                <h5 class="m-auto">{{ truncateText2(`4`, 2) }}</h5>
                                            </v-btn> -->
                                            <v-card :elevation="showCard2 ? 20 : 0" class="ms-auto pending-card"
                                                :class="[{ 'pending-card-transform2': showCard, 'pending-card-transform': showCard2 }]">

                                                <v-card-actions class="m-0 p-0 h-0" min-height="0">
                                                    <v-btn icon @click="showCard2 = !showCard2" class="sideCorner-icon"
                                                        :class="[{ 'sideCorner-icon-transform2': showCard2 }]">
                                                        <h5 :hidden="showCard2" class="m-auto">
                                                            {{ truncateText2(filteredPosts.length, 2) }}
                                                        </h5>
                                                        <h5 :hidden="!showCard2" class="m-auto" style="color: #000;">
                                                            x
                                                        </h5>
                                                    </v-btn>

                                                    <!-- <v-btn :icon="!showCard2" @click="showCard2 = !showCard2"
                                                        class="sideCorner-icon"
                                                        :class="[{ 'sideCorner-icon-transform': showCard, 'sideCorner-icon-transform2': showCard2 }]">
                                                        <h5 class="m-auto">{{ truncateText2(`4`, 2) }}</h5>
                                                    </v-btn> -->
                                                </v-card-actions>

                                                <v-expand-transition>
                                                    <div v-show="showCard2">

                                                        <v-card-text class="m-0 p-2">
                                                            <div class="mb-4 text-header">
                                                                <h5>Left Pending posts</h5>
                                                            </div>

                                                            <div v-if="filteredPosts.length === 0">

                                                                <v-alert text="Currently, this only one post left. No additional pending
                                                                    posts are available at this time."
                                                                    type="info"></v-alert>

                                                            </div>

                                                            <div v-else>
                                                                <!-- <p>{{ data.id }}</p>
                                                                    <p>{{ data.title }}</p>
                                                                    <p>{{ data.type }}</p>
                                                                    <v-img :src="data.image[0]"></v-img> -->
                                                                <div class="post-row mb-3 row"
                                                                    v-for="data in filteredPosts" :key="data.id"
                                                                    @click="clickPost(data.id)">
                                                                    <div class="pi-overlay"></div>
                                                                    <div class="col-3 p-0">
                                                                        <v-img :src="data.image[0]" class="w-100 h-100"
                                                                            cover></v-img>
                                                                    </div>
                                                                    <div class="col-9">
                                                                        <p class="p-0 m-0 mt-1">
                                                                            <span>{{ truncateText(data.title,
                                                                                30)
                                                                                }}</span>

                                                                        </p>
                                                                        <p><span>{{ data.type }} post</span>,
                                                                            uploaded <span>{{
                                                                                calculateDate(data.date)
                                                                            }}</span>
                                                                        </p>

                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </v-card-text>

                                                    </div>
                                                </v-expand-transition>
                                            </v-card>
                                            <!-- right card end -->
                                        </div>

                                        <!-- uploaded user card end -->

                                    </div>
                                </v-card>
                            </div>

                        </div>



                    </div>

                    <!-- left end -->
                </div>


            </div>

            <v-snackbar class="edit-snack" elevation="24" v-model="alert.show" :timeout="alert.timeout"
                :color="alert.color" :bottom="true">
                <v-icon>mdi-exclamation</v-icon>
                {{ alert.message }}

                <v-btn color="info" variant="text" @click="alert.show = false">
                    Close
                </v-btn>

            </v-snackbar>

        </v-container>
    </div>
</template>

<script>
import AES from 'crypto-js/aes'
import Utf8 from 'crypto-js/enc-utf8';
import axios from 'axios';
import Swal from 'sweetalert2';

import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/free-mode';
import 'swiper/css/navigation';
import 'swiper/css/thumbs';
import { Keyboard, Mousewheel, Autoplay, FreeMode, Navigation, Thumbs } from 'swiper/modules';

export default {

    name: 'postDetailView',

    components: {
        Swiper,
        SwiperSlide,
    },

    data: () => ({

        // get all uploader sell post count
        sellPosts: [],
        rentPosts: [],

        // for swiper js 
        thumbsSwiper: null,
        modules: [Keyboard, Mousewheel, Autoplay, FreeMode, Navigation, Thumbs],
        activeIndex: 0,
        showMoreImages: false,
        overlayInEdit: false,
        overlayInEditLoading: false,


        // for uploaded user in admin view
        showCard: false,
        showCard2: false,
        showCard3: false,
        overlay: false,

        user: {
            initials: 'JD',
            fullName: 'John Doe',
            email: 'john.doe@doe.com',
        },

        // search bar staff start
        searchDialog: false,
        search: '',
        menu: false,
        menuSearch: false,
        postTitles: [
            { id: 1, title: 'Welcome Home smatha js auto wind and search bar ' },
            { id: 2, title: 'Wonderful World' },
            { id: 3, title: 'Amazing Grace' },
            { id: 4, title: 'Beautiful Life' },
        ],
        tempPostTitles: [],

        tempAllPendingPosts: [],
        activator: null,
        alert: {
            show: false,
            message: '',
            timeout: 2000, // Duration in milliseconds
            color: 'deep-purple-accent-4' // Change color as needed
        },
        // search bar staff end 


        drawer: false,
        showData: false,
        items: [
            { title: 'Home' },
            { title: 'About' },
            { title: 'Contact' },
        ],
        getData: '',
        getUser: [],
        postGetId: null,

        imageDialog: false,
        sheet: false,
        // Sells: [
        //     { id: 1, title: 'Hello world', img: require('@/assets/img/h1.jpg'), post_type: 'Condo', type: 'sell' },
        //     { id: 2, title: 'Hello Again', img: require('@/assets/img/h3.jpg'), post_type: 'Apartment', type: 'sell' },
        //     { id: 3, title: 'Hello Elephant', img: require('@/assets/img/h5.jpg'), post_type: 'Industrial', type: 'sell' },

        // ],

        avatar: require('@/assets/img/ava5.jpg'),
        savedPosts: [],

        // to keep all datas
        mainPostId: '',
        subUserId: '',
        FetchregisterData: '',

        // get desc from input 
        getDescription: '',

        // get Interested user
        interestedUser: [],


        post: {
            post_type: '',
            province: '',
            region: '',
            country: '',
            latitude: '',
            longitude: '',
            post_id: '',
            title: '',
            description: '',
            property_type: '',
            area: '',
            price: '',
            photo_url: [],
            deposit: '',
            least_contract: ''
        },

        postOwner: {
            name: '',
            email: '',
            phone: '',
        },

        reqDialog: false,
        phoneNumber: '',
        selectedCountry: {
            code: '+95',
            code2: '+66',
            flag: 'https://flagcdn.com/w320/mm.png', // Myanmar flag
            flag2: 'https://flagcdn.com/w320/th.png' // Thailand
        },

        img: require("@/assets/img/1.jpg"),
        img1: require("@/assets/img/2.jpg"),
        img2: require("@/assets/img/3.jpg")
    }),

    mounted() {
        // remove overlay
        this.overlay = false;
        // remove item
        localStorage.removeItem('openTab');

        this.getUser = JSON.parse(sessionStorage.getItem('login_user'));

        const check_link = this.splitData(this.$route.params.id)[1];
        if (check_link === "Success" || check_link === "details") {
            this.fetchPost(this.splitData(this.$route.params.id)[0]);

            // search bar staff
            this.activator = this.$refs.activator;
            this.fetchAllPosts();
        } else {
            this.fetchPost(this.splitData(this.$route.params.id)[0]);
            this.fetchAllPendingPosts();
        }

        // to get Uploader post count 
        if (this.splitData(this.$route.params.id)[1] === "Admin_View") {
            this.fetchAllSellRentPostsToCount();
        }

    },


    computed: {


        phonePrefix() {
            if (this.post.country === 'Thailand') {
                return this.selectedCountry.code2;
            } else if (this.post.country === 'Myanmar') {
                return this.selectedCountry.code;
            } else {
                return ''; // Or any default prefix you want
            }
        },

        // search bar staff
        filteredTitles() {
            if (!this.search) return [];
            return this.tempPostTitles.filter((post) =>
                post.title.toLowerCase().startsWith(this.search.toLowerCase())
            );
        },

        filteredPosts() {
            return this.tempAllPendingPosts.filter(temp => temp.id !== this.post.post_id);
        },

    },

    methods: {
        fetchAllSellRentPostsToCount() {
            if(JSON.parse(sessionStorage.getItem('sub_user'))) {
                const user = JSON.parse(sessionStorage.getItem('sub_user'));
                const subUserId = user.subUserId;
                console.log(subUserId);
                axios.get('http://localhost:8083/posts/allSellPost', {
                params: {
                    subUserId: subUserId
                }
            })
                .then(response => {
                    const sellPosts = [];
                    const rentPosts = [];

                    response.data.forEach(post => {
                        if (post.sellpost != null) {
                            const postId = post.post_id;
                            const title = post.sellpost.title;
                            const photoUrl = Array.isArray(post.sellpost.image) ? post.sellpost.image[0] : post.sellpost.image;

                            sellPosts.push({
                                post_id: postId,
                                title: title,
                                photo_url: photoUrl
                            });
                        } else if (post.rentpost != null) {
                            const postId = post.post_id;
                            const title = post.rentpost.title;
                            const photoUrl = Array.isArray(post.rentpost.image) ? post.rentpost.image[0] : post.rentpost.image;

                            rentPosts.push({
                                post_id: postId,
                                title: title,
                                photo_url: photoUrl
                            });
                        }
                    });
                    this.sellPosts = sellPosts;
                    this.rentPosts = rentPosts;

                    console.log("Number of sell posts received: ", sellPosts.length);
                    console.log(sellPosts);

                    console.log("Number of rent posts received: ", rentPosts.length);
                    console.log(rentPosts);
                })
                .catch(error => {
                    console.error("Error fetching posts: ", error);
                });
            }

        },


        handleShowMoreImages() {
            this.showMoreImages = !this.showMoreImages;
        },
        setThumbsSwiper(swiper) {
            this.thumbsSwiper = swiper;
            swiper.on('slideChange', () => {
                this.activeIndex = swiper.realIndex;
            });
        },

        searchToggle() {
            this.search = '';
            this.showCard3 = !this.showCard3;
        },
        onClickOutside() {
            if (this.showCard3) {
                this.showCard3 = false;
            }
        },

        clickPost(data) {
            this.showCard = false;
            this.showCard2 = false;
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });

            // Wait for the scroll to complete before navigating
            setTimeout(() => {
                const afterEncrypt = this.encryptId(data);
                this.$router.push({ name: 'postDetailView', params: { id: `${afterEncrypt} Admin_View` } });
            }, 400); // Adjust the timeout duration as needed

        },

        calculateDate(data) {
            const currentDate = new Date(); // Get current date
            // Convert the interest date string to a Date object
            const interestDate = new Date(data);
            // Calculate the difference in milliseconds
            const difference = currentDate - interestDate;
            // Convert milliseconds to days
            const daysAgo = Math.floor(difference / (1000 * 60 * 60 * 24));
            // Format the output based on the number of days
            let output;
            if (daysAgo === 0) {
                output = 'today';
            } else if (daysAgo === 1) {
                output = 'yesterday';
            } else {
                output = daysAgo + ' days ago';
            }
            return output;
        },

        showSearchBar() {
            this.searchActive = true;
            this.$nextTick(() => {
                this.$refs.searchField && this.$refs.searchField.focus();
            });
        },

        onSearch() {
            this.menu = !!this.search; // Show the menu only if there is a search query
        },

        onSearchMobile() {
            this.menuSearch = !!this.search;
        },

        handleItemClick(post) {
            this.overlay = true;
            this.showCard3 = false;

            // Check if post ID matches the main post ID
            if (post.id === this.mainPostId) {
                this.alert.message = "This post is already displayed!";
                this.alert.color = '#e86f52';
                this.alert.show = true;

                this.overlay = false;
            } else {

                const afterEncrypt = this.encryptId(post.id);
                // this.$router.push({ name: 'postDetailView', params: { id: `${encryptData} Success` } });
                this.$router.push({ name: 'postDetailView', params: { id: `${afterEncrypt} Success` } });

                setTimeout(() => {
                    this.overlay = false;
                }, 1800);

            }
            this.menu = false;
            this.search = '';


        },


        formatDesc(data) {
            return data.split('\n').join('<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
        },

        // async interest() {
        //     const requestData = {
        //         description: this.getDescription
        //     };

        //     try {
        //         const response = await axios.post(`http://localhost:8083/interest/addNew/${this.getUser.register_id}/${this.mainPostId}`, requestData);

        //         // Check if the request was successful (status 200)
        //         if (response.status == 409) {

        //             console.log("You already interested this post!");

        //         } else if (response.status === 202 || response.status === 200) {

        //             console.log("You interested this post!");
        //         } else {
        //             console.log("Unexpected response:", response.status);
        //         }
        //     } catch (error) {

        //         console.error('Error fetching post:', error);
        //     }
        //     window.location.reload();
        // },

        async fetchInterestedUsers(postId) {
            // then decrypt 
            const decryptId = this.decryptId(postId);
            try {
                const response = await axios.get(`http://localhost:8083/interest/getDataByPostId/${decryptId}`);


                if (response.status === 204) {
                    console.log('No data available for this post.');
                } else {
                    const currentDate = new Date(); // Get current date
                    this.interestedUser = response.data.map(item => {
                        // Convert the interest date string to a Date object
                        const interestDate = new Date(item.interest_date);
                        // Calculate the difference in milliseconds
                        const difference = currentDate - interestDate;
                        // Convert milliseconds to days
                        const daysAgo = Math.floor(difference / (1000 * 60 * 60 * 24));
                        // Format the output based on the number of days
                        let output;
                        if (daysAgo === 0) {
                            output = 'today';
                        } else if (daysAgo === 1) {
                            output = 'yesterday';
                        } else {
                            output = daysAgo + ' days ago';
                        }
                        // Return the item with the formatted date
                        return {
                            postId: item.id,
                            name: item.reg_user.name,
                            daysAgo: output
                        };
                    });
                    console.log("Interested Users:", this.interestedUser);
                }
            } catch (error) {
                console.error('Error fetching posts:', error);
            }
        },

        approve(post) {

            function httpErrorHandler(error) {
                if (axios.isAxiosError(error)) {
                    const response = error?.response
                    if (response) {
                        const statusCode = response?.status
                        if (statusCode === 500 || statusCode === 400) {
                            Swal.fire({
                                title: 'Error!',
                                text: 'Please try again!!!',
                                icon: 'error',
                                customClass: {
                                    confirmButton: 'myCustomErrorButton'
                                },
                                buttonsStyling: false,
                                allowOutsideClick: false,
                                allowEscapeKey: false
                            });
                        }
                    }
                }
            }

            Swal.fire({
                title: 'Are you sure?',
                text: "Do you want to approve this post?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, approve it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        title: 'Processing...',
                        text: 'Please wait while the post is being approved.',
                        icon: 'info',
                        allowOutsideClick: false,
                        allowEscapeKey: false,
                        showConfirmButton: false,
                        onBeforeOpen: () => {
                            Swal.showLoading();
                        }
                    });

                    axios.put("http://localhost:8083/posts/update", post)
                        .then(function (response) {
                            const status = response.status; // No need to parse status
                            if (status === 200) {
                                Swal.fire({
                                    title: 'Successfully Approved!',
                                    icon: 'success',
                                    customClass: {
                                        confirmButton: 'myCustomSuccessButton'
                                    },
                                    buttonsStyling: false,
                                    allowOutsideClick: false,
                                    allowEscapeKey: false
                                }).then(() => {
                                    window.location.reload();
                                });
                            }
                        })
                        .catch(httpErrorHandler);
                }
            });
        },

        cancel(post) {

            function httpErrorHandler(error) {
                if (axios.isAxiosError(error)) {
                    const response = error?.response;
                    if (response) {
                        const statusCode = response?.status;
                        if (statusCode === 500 || statusCode === 400) {
                            Swal.fire({
                                title: 'Error!',
                                text: 'Please try again!!!',
                                icon: 'error',
                                customClass: {
                                    confirmButton: 'myCustomErrorButton'
                                },
                                buttonsStyling: false,
                                allowOutsideClick: false,
                                allowEscapeKey: false
                            });
                        }
                    }
                }
            }

            Swal.fire({
                title: 'Are you sure?',
                text: "Do you want to decline this post?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, decline it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        title: 'Processing...',
                        text: 'Please wait while the post is being declined.',
                        icon: 'info',
                        allowOutsideClick: false,
                        allowEscapeKey: false,
                        showConfirmButton: false,
                        onBeforeOpen: () => {
                            Swal.showLoading();
                        }
                    });

                    axios.put("http://localhost:8083/posts/decline", post)
                        .then(function (response) {
                            const status = response.status; // No need to parse status
                            if (status === 200) {
                                Swal.fire({
                                    title: 'Successfully Canceled!',
                                    icon: 'success',
                                    customClass: {
                                        confirmButton: 'myCustomButton'
                                    },
                                    buttonsStyling: false,
                                    allowOutsideClick: false,
                                    allowEscapeKey: false
                                }).then(() => {
                                    window.location.reload();
                                });
                            }
                        })
                        .catch(httpErrorHandler);
                }
            });
        },

        // approve(post) {

        //     function httpErrorHandler(error) {
        //         if (axios.isAxiosError(error)) {
        //             const response = error?.response
        //             if (response) {
        //                 const statusCode = response?.status
        //                 if (statusCode === 500) { alert("Please try again!!!") }
        //                 else if (statusCode === 400) { alert("Please try again!!!") }

        //             }
        //         }
        //     }

        //     axios.put("http://localhost:8083/posts/update", post)
        //         .then(function (response) {
        //             const status = JSON.parse(response.status);
        //             if (status == '200') {
        //                 Swal.fire({
        //                     title: 'Successfully Approved!',
        //                     icon: 'success',
        //                     customClass: {
        //                         confirmButton: 'myCustomSuccessButton'
        //                     },
        //                     buttonsStyling: false,
        //                     allowOutsideClick: false,
        //                     allowEscapeKey: false
        //                 }).then(() => {
        //                     window.location.reload();
        //                 });
        //             }
        //         })
        //         .catch(httpErrorHandler)
        // },

        // cancel(post) {

        //     function httpErrorHandler(error) {
        //         if (axios.isAxiosError(error)) {
        //             const response = error?.response
        //             if (response) {
        //                 const statusCode = response?.status
        //                 if (statusCode === 500) { alert("Please try again!!!") }
        //                 else if (statusCode === 400) { alert("Please try again!!!") }

        //             }
        //         }
        //     }

        //     axios.put("http://localhost:8083/posts/decline", post)
        //         .then(function (response) {
        //             const status = JSON.parse(response.status);
        //             if (status == '200') {
        //                 Swal.fire({
        //                     title: 'Successfully Canceled!',
        //                     icon: 'success',
        //                     customClass: {
        //                         confirmButton: 'myCustomButton'
        //                     },
        //                     buttonsStyling: false,
        //                     allowOutsideClick: false,
        //                     allowEscapeKey: false
        //                 }).then(() => {
        //                     window.location.reload();
        //                 });
        //                 alert("Canceled  Successfully")

        //             }
        //         })
        //         .catch(httpErrorHandler)
        // },

        async interest() {
            const requestData = {
                description: this.getDescription
            };

            if (this.getUser.register_id === this.FetchregisterData.register_id) {
                this.reqDialog = false;
                Swal.fire({
                    icon: 'warning',
                    title: 'Warning',
                    text: "You can't make interest to your own post!",
                    allowOutsideClick: false,

                });
            } else {

                try {
                    // Show loading indicator
                    Swal.fire({
                        title: 'Processing...',
                        text: 'Please wait while we process your interest.',
                        allowOutsideClick: false,
                        didOpen: () => {
                            Swal.showLoading();
                        }
                    });

                    const response = await axios.post(
                        `http://localhost:8083/interest/addNew/${this.getUser.register_id}/${this.mainPostId}`,
                        requestData
                    );

                    // Close the loading indicator
                    Swal.close();

                    if (response.status === 202 || response.status === 200) {
                        this.reqDialog = false;
                        Swal.close();
                        Swal.fire({
                            icon: 'success',
                            title: 'Success!',
                            text: 'You make interested in this post.'
                        });
                    } else {
                        Swal.close();
                        Swal.fire({
                            icon: 'error',
                            title: 'Unexpected response',
                            text: `Unexpected response: ${response.status}`
                        });
                    }
                } catch (error) {
                    // Close the loading indicator
                    Swal.close();
                    this.reqDialog = false;
                    if (error.response) {

                        if (error.response.status === 409 || error.response.status === 406 || error.response.status === 400 || error.response.status === 403) {

                            Swal.fire({
                                icon: 'error',
                                title: 'Relax bro!',
                                text: 'You already made an interest in this post!',
                                showCancelButton: false, // Hide the cancel button
                                allowOutsideClick: true, // Allow clicking outside to close
                            }).then((result) => {
                                if (result.isConfirmed || result.isDismissed) {
                                    window.location.reload();
                                }
                            });

                        } else if (error.request) {
                            // Request was made but no response received
                            Swal.fire({
                                icon: 'error',
                                title: 'Network Error',
                                text: 'No response from the server. Please check your network connection.'
                            });
                        } else {
                            // Something else happened while setting up the request
                            Swal.fire({
                                icon: 'error',
                                title: 'Error',
                                text: `Error: ${error.message}`
                            });
                        }
                    }

                }
            }



        },


        // async submitForm() {
        //     console.log("submit form reached")
        //     const formData = new FormData();
        //     formData.append('name', this.name);
        //     formData.append('gmail', this.gmail);
        //     formData.append('gender', this.gender);
        //     formData.append('phone', this.phone);
        //     formData.append('dob', this.dob);
        //     formData.append('image', this.save_profile);


        //     try {
        //         const response = await axios.post('http://localhost:8090/std/save', formData);
        //         console.log('Data saved successfully:', response.data);

        //         this.initialize();


        //     } catch (error) {
        //         console.error('Error saving data:', error);
        //     }

        // },

        getPostType(data) {
            const typeLetter = data.charAt(0);
            if (typeLetter === 's') {
                return "Sell";
            } else {
                return "Rent";
            }
        },

        splitData(data) {
            this.getData = data.split(' ')[1];

            // call fetch interersted user
            if (this.getData === 'details') {
                this.fetchInterestedUsers(data.split(' ')[0]);
            }

            return data.split(' ');
        },

        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + '...';
            }
            return text;
        },

        truncateText2(text, charLimit) {
            if (text.length > charLimit) {
                return '99+';
            }
            return text;
        },


        openImageDialog() {
            this.imageDialog = true;
        },
        closeImageDialog() {
            this.imageDialog = false;
        },
        toggleSaved(postId) {
            const index = this.savedPosts.indexOf(postId);
            if (index !== -1) {
                this.savedPosts.splice(index, 1);
            } else {
                this.savedPosts.push(postId);
            }
        },
        isSaved(postId) {
            return this.savedPosts.includes(postId);
        },

        encryptId(id) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on'
            const encryptedId = AES.encrypt(id.toString(), secretKey).toString()
            return encryptedId
        },

        decryptId(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptedId = decryptedBytes.toString(Utf8);
            return parseInt(decryptedId, 10);
        },

        decryptData(encryptedId) {
            const secretKey = 'post-detail-view-secret-code-havenly-2024-still-go-on';
            const decryptedBytes = AES.decrypt(encryptedId, secretKey);
            const decryptData = decryptedBytes.toString(Utf8);
            return decryptData;
        },

        openDialog() {
            if (this.getUser) {
                this.reqDialog = true;
            } else {
                Swal.fire({
                    icon: 'warning',
                    title: 'Login Required',
                    text: 'You need to login to access this feature.',
                    showCancelButton: true,
                    confirmButtonText: 'Login',
                    cancelButtonText: 'Cancel',
                }).then((result) => {
                    if (result.isConfirmed) {
                        this.$router.push({ name: 'login' });
                    }
                });
            }
        },

        closeDialog() {
            this.reqDialog = false;
        },

        // get all pending posts 
        async fetchAllPendingPosts() {
            try {
                const response = await fetch('http://localhost:8083/posts/allPending');
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                // Parse the response as JSON
                const data = await response.json();

                // Initialize an empty array to store post data
                const tempPosts = [];
                let tempImages = [];
                let tempDate = '';

                // Iterate over each post in the response data
                data.forEach(postData => {
                    // Extract relevant data for each post
                    const temp_post_id = postData.post_id;
                    const temp_post_title = postData.sellpost ? postData.sellpost.title : postData.rentpost ? postData.rentpost.title : '';
                    const temp_post_type = postData.sellpost ? 'Sell' : postData.rentpost ? 'Rent' : '';
                    if (postData.sellpost) {
                        tempImages = postData.sellpost.image;
                        tempDate = postData.sellpost.date;
                    } else if (postData.rentpost) {
                        tempImages = postData.rentpost.image;
                        tempDate = postData.rentpost.date;
                    }

                    const temp_images = Array.isArray(tempImages) ? tempImages : [tempImages];
                    // Push the extracted data into the tempPostTitles array
                    tempPosts.push({
                        id: temp_post_id,
                        title: temp_post_title,
                        type: temp_post_type,
                        image: temp_images,
                        date: tempDate,
                    });
                });

                // Update the component's tempPostTitles property
                this.tempAllPendingPosts = tempPosts;
            } catch (error) {
                console.error('Error fetching posts:', error);
            }
        },

        // for search staff 
        async fetchAllPosts() {
            try {
                const response = await fetch('http://localhost:8083/posts/allComplete');
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                // Parse the response as JSON
                const data = await response.json();

                // Initialize an empty array to store post data
                const tempPostTitles = [];


                // Iterate over each post in the response data
                data.forEach(postData => {
                    // Extract relevant data for each post
                    const temp_post_id = postData.post_id;
                    const temp_post_title = postData.sellpost ? postData.sellpost.title : postData.rentpost ? postData.rentpost.title : '';
                    const temp_post_type = postData.sellpost ? 'Sell' : postData.rentpost ? 'Rent' : '';

                    // Push the extracted data into the tempPostTitles array
                    tempPostTitles.push({
                        id: temp_post_id,
                        title: temp_post_title,
                        type: temp_post_type,
                    });
                });

                // Update the component's tempPostTitles property
                this.tempPostTitles = tempPostTitles;
            } catch (error) {
                console.error('Error fetching posts:', error);
            }
        },


        async fetchPost(postId) {

            // then decrypt 
            const decryptId = this.decryptId(postId);

            try {
                const response = await fetch(`http://localhost:8083/posts/getPostById/${decryptId}`);

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                // Parse the response as JSON
                const data = await response.json();

                // Taking Post Main id
                this.mainPostId = data.post_id;

                // Taking Subuser id 
                this.subUserId = data.subUser.subUserId;

                // call the function to get registere user
                this.fetchRegisterUser(this.subUserId);

                // Check the post type
                if (data.sellpost) {
                    this.processPostData(data.sellpost, data);
                } else if (data.rentpost) {
                    this.processPostData(data.rentpost, data);
                } else {
                    console.error('Unexpected data format:', data);
                }
            } catch (error) {
                console.error('Error fetching post:', error);
            }
        },
        processPostData(postData, upperData) {
            const imageUrls = Array.isArray(postData.image) ? postData.image : [postData.image];
            this.post = {
                post_type: upperData.post_type,
                province: postData.locations.province,
                region: postData.locations.region,
                country: postData.locations.countries.country_name,
                latitude: postData.locations.latitude,
                longitude: postData.locations.longitude,
                post_id: upperData.post_id,
                title: postData.title,
                description: postData.description,
                property_type: postData.property_type,
                status: upperData.status,
                area: postData.area,
                price: postData.price,
                photo_url: imageUrls,
                deposit: postData.deposit || '',
                least_contract: postData.least_contract || ''
            };

            this.postOwner = {
                name: upperData.subUser.reg_user.name,
                email: upperData.subUser.reg_user.email,
                phone: upperData.subUser.reg_user.phone,
            }
            this.activeIndex = 0;
        },

        async fetchRegisterUser(id) {
            try {
                const response = await fetch(`http://localhost:8083/getDataBySubId/${id}`);

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                this.FetchregisterData = await response.json();

            } catch (error) {
                console.error('Error fetching post:', error);
            }
        },


    },

    watch: {
        '$route.params.id': {
            handler() {
                this.fetchPost(this.splitData(this.$route.params.id)[0]);
            },
            immediate: true,
        },
        showMoreImages(newValue) {
            if (newValue) {
                this.overlayInEditLoading = true;
                setTimeout(() => {
                    this.overlayInEditLoading = false;
                    if (!this.overlayInEditLoading) {
                        this.activeIndex = 0;
                    }
                }, 1200);
            }
        },
    },

}
</script>


<style lang="scss" scoped>
.v-btn-sheet {
    border-radius: 10px !important;
    padding-bottom: 10px;

    .v-bottom-sheet__content.v-overlay__content {
        .v-btn-sheet-card {
            border-radius: 10px;
        }
    }
}




.uploaded-user-container {

    .uploaded-user-card {
        width: 80%;
        height: 56px;
        border: 3px solid #e86f52;
        transition: all 0.2s ease-in-out;
        border-radius: 28px;
        background-color: #eee;

        .uploaded-card-action {
            position: relative;

            .ava-img {
                width: 120px;
                height: 120px;
                overflow: hidden;
                border-radius: 50%;
                border-width: 5px;
                border-style: solid;
                transition: all 0.3s ease-in-out;
                transform: translateY(10%) translateX(90%);
                //border-color: #e86f52 #e86f52 #e86f52 #525252;
                border-color: #e86f52;
                //transform: rotate(-45deg);
                //box-shadow: 0px 6px 26px 1px rgba(0, 0, 0, 0.3);

                .v-img {
                    //transform: rotate(-45deg);
                    width: 100%;
                    height: 100%;
                    border-radius: 50%;
                    border: 4px solid #fff;
                }
            }

            .ava-data {

                /*transition: all 0.3s ease-in-out;
                transform: translateY(84px);
                text-align: center;
                margin: 0 auto;
                font-weight: bold;
                color: #e86f52;
                font-size: 24px;*/

                display: none;
            }

            .action-close {
                position: absolute;
                top: 0;
                right: 0;

                .v-icon {
                    color: #000;
                }
            }
        }

        .action-group-p {
            .v-btn {
                text-transform: capitalize;
                position: relative;
                color: #000;
                background: radial-gradient(circle at 50% 120%,
                        #e86f52,
                        #e86f52 10%,
                        #e86f52 80%,
                        #e86f52 100%);

                .v-badge {
                    position: absolute;
                    top: 0;
                    right: 0;
                    margin-top: -10px;
                    margin-right: -10px;
                    z-index: 1;
                }
            }

            .v-btn:before {
                content: "";
                position: absolute;
                top: 1%;
                left: 5%;
                width: 90%;
                height: 90%;
                border-radius: 50%;
                background: radial-gradient(circle at 50% 0px,
                        #ffffff,
                        rgba(255, 255, 255, 0) 58%);
                filter: blur(6px);
                z-index: 2;
            }


        }

    }

    .uploaded-user-card.uploaded-user-card-transform {
        border: none;
        width: 100%;
        height: 285px;
        position: relative;
        overflow: hidden;
        border-radius: 12px;
        background-color: #fff;
        transition: all 0.2s ease-in-out;
    }

    .uploaded-user-card.uploaded-user-card-transform::before,
    .uploaded-user-card.uploaded-user-card-transform::after {
        content: '';
        position: absolute;
    }

    .uploaded-user-card.uploaded-user-card-transform::before {
        width: 100%;
        height: 38%;
        background-color: #e86f52;
        transition: all 0.3s ease-in-out;
    }

    .uploaded-user-card.uploaded-user-card-transform2 {
        position: absolute;
        top: 0;
        left: 0;
        margin: 4px;
        opacity: 0;
        width: 0px;
        height: 0px;
        border: none;

        .uploaded-card-action {
            display: none;
        }

    }

    // pending card start
    .pending-card {
        width: 60px;
        height: 60px;
        transition: all 0.2s ease-in-out;

        .sideCorner-icon {
            width: 50px;
            height: 50px;
            display: flex;
            justify-content: center;
            align-items: center;
            position: absolute;
            top: 0;
            right: 0;
            margin: 4px;
            text-transform: capitalize;
            color: #000;
            background: radial-gradient(circle at 50% 120%, #e86f52, #e86f52 10%, #e86f52 80%, #e86f52 100%);
            transition: all 0.3s ease-in-out;
        }

        .sideCorner-icon::before {
            content: "";
            position: absolute;
            top: 1%;
            left: 5%;
            width: 90%;
            height: 90%;
            border-radius: 50%;
            background: radial-gradient(circle at 50% 0px, #ffffff, rgba(255, 255, 255, 0) 58%);
            filter: blur(6px);
            z-index: 2;
            transition: all 0.3s ease-in-out;
        }

        .sideCorner-icon.sideCorner-icon-transform2 {
            background: transparent;
            position: absolute;
            right: 0;
            top: 0;
            margin-top: -3px;
        }

        .sideCorner-icon::before.sideCorner-icon-transform2 {
            background: transparent !important;
            filter: none !important;
        }

        .text-header {
            text-align: center;
            margin-top: -52px;
            color: #fff;

            .v-icon {
                color: #525252 !important;
            }

            span {
                color: #525252;
            }
        }


        .post-row {
            border-radius: 6px;
            overflow: hidden;
            width: 100%;
            margin: auto;
            border: 1px solid #525252;
            cursor: pointer;
            position: relative;
            height: 60px;

            .pi-overlay {
                position: absolute;
                width: 100%;
                height: 100%;
                left: 0;
                top: 0;
                z-index: 1;
                background-color: rgba(0, 0, 0, 0.2);
                display: none;
                transition: all 0.4s ease-in-out;
            }

            p {
                font-size: 14px;

                span {
                    color: #e86f52;
                }
            }

            &:hover .pi-overlay {
                display: block;
            }
        }

    }

    // expand
    .pending-card.pending-card-transform {
        width: 100%;
        height: 285px;
        overflow-y: scroll;
        scrollbar-width: none;
        /* Firefox */
        transition: all 0.2s ease-in-out;
        z-index: 2;
        border-radius: 12px;
        position: relative;
        box-shadow: 0px 6px 20px 1px rgba(0, 0, 0, 0.4);
    }

    .pending-card.pending-card-transform::before,
    .pending-card.pending-card-transform::after {
        content: '';
        position: absolute;
    }

    .pending-card.pending-card-transform::before {
        width: 100%;
        height: 45px;
        background-color: #e86f52;
        transition: all 0.2s ease-in-out;
    }


    // disappear
    .pending-card.pending-card-transform2 {
        width: 0px;
        height: 0px;
        opacity: 0;
        position: absolute;
        top: 0;
        right: 0;
        margin: 4px;
    }



}
</style>