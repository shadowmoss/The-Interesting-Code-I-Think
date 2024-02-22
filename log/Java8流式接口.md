## JAVA8流式接口
如果我们向流中请求对象，但是流中什么都没有，这时会发生什么呢？我们喜欢把流连接成“ 乐通道”(happy path) ，并假
设没有什么会中断它 然而在流中放入一个null就能轻松破坏掉它 有没有某种我们可
以使用的 象,既可以作为流元素来占位，也可以在我们要找的元素不存在时友好地告知
我们(也就是说，不会抛出异常)？  
这个想法被实现为Optional类型 某些标准的流操作会返回Optional对象，因为它
们不能确保所要的结果一定存在 这些流操作列举如下:  
reduce()的一个版本，它并不以一个“identity”对象作为其第一个参数(在reduce()
的其他版本中，“identity”对象会成为默认结果，所以不会有结果为空的风险),
它会将返回值包在一个Optional中
* findFirst()返回包含第一个元素的Optional。如果这个流为空，则返回Optional.empty。
* findAny()返回包含任何元素的Optional,如果这个流为空，则返回Optional.empty
* max()和min()分别返回包含流中最大值和最小值的Optional。如果这个流为空,则返回Optional.empty。
* reduce()的一个版本，它并不以一个“identity”对象作为其第一个参数(在reduce()的其他版本中，“identity”对象会成为默认结果，所以不会有结果为空的风险),
  它会将返回值包在一个Optional中
* 对于数值化的流IntStream, LongStream和DoubleStream, average()操作将其结果包在一个Optional中，以防流为空的情况。
