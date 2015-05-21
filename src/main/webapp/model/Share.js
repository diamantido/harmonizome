App.Model.Share = Backbone.Model.extend({
    defaults: [
        {
            name: 'twitter',
            link: 'http://twitter.com/share',
            image: 'image/share/twitter.png'
        },
        {
            name: 'facebook',
            link: 'http://www.facebook.com/sharer/sharer.php?u=http://www.maayanlab.net/harmonizome/',
            image: 'image/share/facebook.png'
        },
        {
            name: 'google-plus',
            link: 'https://plus.google.com/share?url=http://www.maayanlab.net/harmoziome',
            image: 'image/share/google-plus.png'
        },
        {
            name: 'linkedin',
            link: 'https://www.linkedin.com/shareArticle?url=http://www.maayanlab.net/harmonizome',
            image: 'image/share/linkedin.png'
        }
    ]
});
