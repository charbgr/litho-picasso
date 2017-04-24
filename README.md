# Litho - Picasso
Litho Picasso is a Litho compatible library, which provides an Image Component compatible with Picasso. Litho-Picasso supports a wide part of Picasso's functionality. If you notice that anything is missing ping us :)

## How to use

Add the following dependency on your app's `build.gradle`:
```gradle
compile 'com.github.charbgr:litho-picasso:1.0'
```

Add PicassoImage component on your own Component :)

```java
PicassoImage.create(c)
    .imageUrl(image)
    .fit(true)
    .centerCrop(true)
    .buildWithLayout();
```

[Here](https://github.com/charbgr/litho-picasso/blob/master/app/src/main/java/com/github/charbgr/litho_picasso_component_sample/lithography/PicassoSingleImageComponentSpec.java) you can find a sample ComponentSpec that uses PicassoImage component.


## Other

Litho repository: https://github.com/facebook/litho

Litho documentation: http://fblitho.com/docs/getting-started

License
=======

    MIT License

    Copyright (c) 2017 Vasilis Charalampakis & Pavlos-Petros Tournaris

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
