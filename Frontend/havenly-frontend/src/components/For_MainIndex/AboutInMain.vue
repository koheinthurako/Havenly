<!-- <template>
    <dir class="about-in-main">
        <v-container>
            <h3 style="color:#e86f52;" class="ms-4 p-0 d-block d-sm-none">Some user guides ...</h3>
            <div class="row">
                <div class="col-md-4 col-lg-3 col-sm-12">
                    <div class="card-box">



                        <div class="overlay-circle">
                        </div>

                        <div @click="goTo('createAcc')" class="inner-box">
                            <div class="top-cricle-container">
                                <div class="top-circle">
                                    <v-icon>mdi-account</v-icon>
                                </div>
                                <div class="bg-drop"></div>
                            </div>

                            <div class="header">
                                <h4>Create Account</h4>
                            </div>
                            <div class="content">

                                {{ truncateText(`To log in, click "Login" in the navbar and enter your credentials. If
                                you don't have an account, click "Create Account" on the login page, fill in the
                                required details, and submit to register.`, 159) }}

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-12">
                    <div class="card-box">
                        <div class="overlay-circle">
                        </div>

                        <div @click="goTo" class="inner-box">
                            <div class="top-cricle-container">
                                <div class="top-circle">
                                    <v-icon>mdi-cart</v-icon>
                                </div>
                                <div class="bg-drop"></div>
                            </div>
                            <div class="header">
                                <h4>Buying Package</h4>
                            </div>
                            <div class="content">

                                {{ truncateText(`After creating an account, you can buy a package. There have multiple
                                packages we offered with fair prices and unique process.`, 140) }}

                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-12">
                    <div class="card-box">
                        <div class="overlay-circle">
                        </div>


                        <div @click="goTo" class="inner-box">
                            <div class="top-cricle-container">
                                <div class="top-circle">
                                    <v-icon>mdi-upload</v-icon>
                                </div>
                                <div class="bg-drop"></div>
                            </div>
                            <div class="header">
                                <h4>Uploading posts</h4>
                            </div>
                            <div class="content">

                                {{ truncateText(`After subscribed a package successfully, you can sell or rent your
                                properties by creating posts. Creating process are simple.`, 150) }}

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-12">
                    <div class="card-box">
                        <div class="overlay-circle">
                        </div>

                        <div @click="goTo" class="inner-box">
                            <div class="top-cricle-container">
                                <div class="top-circle">
                                    <v-icon>mdi-bookmark-outline</v-icon>
                                </div>
                                <div class="bg-drop"></div>
                            </div>
                            <div class="header">
                                <h4>Making interest</h4>
                            </div>
                            <div class="content">

                                {{ truncateText(`If you interested a post you can request owner to get more info of post
                                or you can contact directly to make discussion. `, 150) }}

                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </v-container>
    </dir>
</template> -->


<template>
    <div class="about-in-main-2">
        <div class="container">
            <div class="card-hover-container">
                <div v-for="(card, index) in cards" :key="index" class="card-hover">
                    <div class="card-hover__content">
                        <h3 class="card-hover__title">
                            {{ card.title }}
                            <span>{{ card.titleSpan }}</span>
                        </h3>
                        <p class="card-hover__text">{{ card.text }}</p>
                        <a href="#" class="card-hover__link">
                            <span>{{ card.linkLabel }}</span>
                            <svg fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                    d="M13.5 4.5L21 12m0 0l-7.5 7.5M21 12H3" />
                            </svg>
                        </a>
                    </div>
                    <v-img :src="card.imageSrc" class="img" :alt="card.imageAlt" cover />
                </div>
            </div>
        </div>

    </div>

</template>

<script>
export default {
    name: 'CardHover',
    data() {
        return {
            cards: [
                {
                    imageSrc: require('@/assets/img/aboutInVue.jpg'),
                    imageAlt: 'Card Image 1',
                    title: 'Create your ',
                    titleSpan: 'account',
                    text: `To log in, click "Login" in the navbar and enter your credentials. If
                                you don't have an account, click "Create Account" on the login page, fill in the
                                required details, and submit to register.`,
                    linkLabel: 'Read How',

                },
                {
                    imageSrc: require('@/assets/img/h10.jpg'),
                    imageAlt: 'Card Image 2',
                    title: 'Explore at',
                    titleSpan: 'Havenly',
                    text: `After creating an account, you can buy a package. There have multiple
                                packages we offered with fair prices and unique process.`,
                    linkLabel: 'Read More',

                },
                {
                    imageSrc: require('@/assets/img/house-2.jpg'),
                    imageAlt: 'Card Image 3',
                    title: 'Package ',
                    titleSpan: 'Subscription',
                    text: `After subscribed a package successfully, you can sell or rent your
                                properties by creating posts. Creating process are simple.`,
                    linkLabel: 'Read More',

                },
                {
                    imageSrc: require('@/assets/img/house-4.jpg'),
                    imageAlt: 'Card Image 4',
                    title: 'Make post',
                    titleSpan: 'interest',
                    text: `If you interested a post you can request owner to get more info of post
                                or you can contact directly to make discussion.`,
                    linkLabel: 'Read More',

                }
            ]
        };
    }
};
</script>