<template>
    <dir class="about-in-main">
        <div class="container">
            <h3 style="color:#e86f52;" class="ms-4 p-0 d-block d-sm-none">Some user guides ...</h3>
            <div class="row">
                <div class="col-md-3 col-12">
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

        </div>
    </dir>
</template>

<script>
export default {
    name: 'AboutInMain',

    date: () => ({

    }),

    methods: {
        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + ' read more ...';
            }
            return text;
        },

        goTo() {
            // this.$router.push({ name: 'About' });
            this.$router.push({ name: 'About' });
        }
    }
}

</script>